package volleyballscore.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.Text
import volleyballscore.components.ScoreCounter
import volleyballscore.components.ServingIcon
import volleyballscore.components.SetCounter
import volleyballscore.components.TeamButton
import volleyballscore.components.TeamsNames
import volleyballscore.models.VolleyBallScoreViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

const val HOME_TEAM = 0
const val VISITOR_TEAM = 1

@Composable
fun MainScreen(
    viewModel: VolleyBallScoreViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = if (uiState.isMatchPoint.value) "Match Point! \uD83C\uDFC6" else "",
                textAlign = TextAlign.Center,
            )
            ScoreCounter(modifier = Modifier)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ServingIcon(HOME_TEAM)
                SetCounter(
                    modifier = Modifier
                        .width(80.dp)
                )
                ServingIcon(VISITOR_TEAM)
            }
            // Extra padding on the bottom
            Box(
                modifier = Modifier
                    .background(color = Color.Red)
                    .height(20.dp)
            )
        }
        // Buttons
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TeamButton(
                Modifier.weight(1f),
                HOME_TEAM,
            )
            TeamButton(
                Modifier.weight(1f),
                VISITOR_TEAM,
            )
        }
        // Bottom text
        TeamsNames()
    }
}

