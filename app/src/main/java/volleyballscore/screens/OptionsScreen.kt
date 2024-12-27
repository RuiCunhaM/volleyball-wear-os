package volleyballscore.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionsScreen(
    scrollScope: CoroutineScope,
    pagerState: PagerState,
    scoreCount: SnapshotStateList<Int>,
    setCount: SnapshotStateList<Int>,
    serving: SnapshotStateList<Boolean>,
    matchPoint: MutableState<Boolean>
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.width(100.dp),
            colors = ButtonDefaults.secondaryButtonColors(),
            onClick = {
                scoreCount.replaceAll { 0 }
                serving.replaceAll { false }
                matchPoint.value = false
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
                10.dp
            )
        )
        Button(
            modifier = Modifier.width(100.dp),
            colors = ButtonDefaults.secondaryButtonColors(),
            onClick = {
                scoreCount.replaceAll { 0 }
                setCount.replaceAll { 0 }
                serving.replaceAll { false }
                matchPoint.value = false
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
