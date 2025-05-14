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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import volleyballscore.components.ScoreCounter
import volleyballscore.components.ServingIcon
import volleyballscore.components.SetCounter
import volleyballscore.components.TeamButton
import volleyballscore.components.TeamsNames

@Composable
fun MainScreen(
    scoreCount: SnapshotStateList<List<Int>>,
    setCount: SnapshotStateList<Int>,
    serving: SnapshotStateList<Boolean>,
    matchPoint: MutableState<Boolean>
) {
    // Score and Set counters
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = if (matchPoint.value) "Match Point! \uD83C\uDFC6" else "",
                textAlign = TextAlign.Center,
            )
            ScoreCounter(
                modifier = Modifier,
                scoreCount
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ServingIcon(serving, 0)
                SetCounter(
                    modifier = Modifier
                        .width(80.dp),
                    setCount
                )
                ServingIcon(serving, 1)
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
                0,
                scoreCount,
                setCount,
                serving,
                matchPoint
            )
            TeamButton(
                Modifier.weight(1f),
                1,
                scoreCount,
                setCount,
                serving,
                matchPoint
            )
        }

        // Bottom text
        TeamsNames()
    }
}

