package volleyballscore.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import volleyballscore.models.VolleyBallScoreViewModel

@Composable
fun OptionsScreen(
    scrollScope: CoroutineScope,
    pagerState: PagerState,
    viewModel: VolleyBallScoreViewModel = viewModel()
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier
                .width(100.dp),
            colors = ButtonDefaults.secondaryButtonColors(),
            onClick = {
                viewModel.undoScore()
                scrollScope.launch { pagerState.animateScrollToPage(0) }
            },
        ) {
            Text(
                text = "Undo",
                textAlign = TextAlign.Center
            )
        }
        Box(
            Modifier.size(
                5.dp
            )
        )
        Button(
            modifier = Modifier
                .width(100.dp)
                .height(40.dp),
            colors = ButtonDefaults.secondaryButtonColors(),
            onClick = {
                viewModel.resetScore()
                scrollScope.launch { pagerState.animateScrollToPage(0) }
            },
        ) {
            Text(
                text = "Reset Score",
                textAlign = TextAlign.Center
            )
        }
        Box(
            Modifier.size(
                5.dp
            )
        )
        Button(
            modifier = Modifier
                .width(100.dp)
                .height(40.dp),
            colors = ButtonDefaults.secondaryButtonColors(),
            onClick = {
                viewModel.resetAll()
                scrollScope.launch { pagerState.animateScrollToPage(0) }
            },
        ) {
            Text(
                text = "Reset ALL",
                textAlign = TextAlign.Center
            )
        }
    }
}
