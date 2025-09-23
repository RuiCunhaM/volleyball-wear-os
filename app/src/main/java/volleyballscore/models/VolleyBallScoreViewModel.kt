package volleyballscore.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class VolleyBallScoreState(
    val scoreCounter: MutableList<List<Int>> = mutableStateListOf(listOf(0, 0)),
    val setCounter: MutableList<Int> = mutableStateListOf(0, 0),
    val isServing: MutableList<Boolean> = mutableStateListOf(false, false),
    val isMatchPoint: MutableState<Boolean> = mutableStateOf(false)
)

class VolleyBallScoreViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VolleyBallScoreState())
    val uiState: StateFlow<VolleyBallScoreState> = _uiState.asStateFlow()

    fun score(teamIndex: Int) {
        val state = uiState.value
        var currentScore = state.scoreCounter.last().toMutableList()
        val otherTeamIndex = teamIndex xor 1;

        // Update score
        currentScore[teamIndex]++;

        // Update serving indicators
        state.isServing[teamIndex] = true;
        state.isServing[otherTeamIndex] = false;

        // Game ended?
        if (currentScore[teamIndex] >= 25 &&
            (currentScore[teamIndex] - currentScore[otherTeamIndex] >= 2)
        ) {
            currentScore = mutableListOf(0, 0)
            state.scoreCounter.clear()
            state.setCounter[teamIndex]++;
            state.isServing[teamIndex] = false;
            state.isServing[otherTeamIndex] = false;
        }

        // Update match point indicator
        state.isMatchPoint.value =
            (currentScore[teamIndex] >= 24 || currentScore[otherTeamIndex] >= 24) &&
                    (currentScore[teamIndex] != currentScore[otherTeamIndex])

        // Update with new score
        state.scoreCounter.add(currentScore)
    }

    fun undoScore() {
        val state = uiState.value

        if (state.scoreCounter.size > 1) {
            state.isMatchPoint.value = false
            state.scoreCounter.removeAt(state.scoreCounter.lastIndex)
            state.isServing.replaceAll { false }
        }
    }

    fun resetScore() {
        val state = uiState.value

        state.scoreCounter.clear()
        state.scoreCounter.add(listOf(0, 0))
        state.isServing.replaceAll { false }
        state.isMatchPoint.value = false
    }

    fun resetAll() {
        resetScore()
        uiState.value.setCounter.replaceAll { 0 }
    }
}