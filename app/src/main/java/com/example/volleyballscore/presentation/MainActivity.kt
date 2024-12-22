package com.example.volleyballscore.presentation

import android.R.style.Theme_DeviceDefault
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.PageIndicatorState
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.volleyballscore.presentation.theme.VolleyballScoreTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(Theme_DeviceDefault)

        setContent {
            VolleyballApp()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VolleyballApp() {
    val scoreCount = remember { mutableStateListOf(0, 0) }
    val setCount = remember { mutableStateListOf(0, 0) }
    val serving = remember { mutableStateListOf(false, false) }
    val matchPoint = remember { mutableStateOf(false) }
    val scrollScope = rememberCoroutineScope()

    VolleyballScoreTheme {
        val pagerState = rememberPagerState(pageCount = { 2 })
        val pageIndicatorState: PageIndicatorState = remember {
            object : PageIndicatorState {
                override val pageOffset: Float
                    get() = pagerState.currentPageOffsetFraction
                override val selectedPage: Int
                    get() = pagerState.currentPage
                override val pageCount: Int
                    get() = 2
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            pageIndicator = {
                HorizontalPageIndicator(
                    pageIndicatorState = pageIndicatorState
                )
            },
            timeText = {
                TimeText()
            },
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> {
                        MainScreen(scoreCount, setCount, serving, matchPoint)
                    }

                    1 -> {
                        OptionsScreen(
                            scrollScope,
                            pagerState,
                            scoreCount,
                            setCount,
                            serving,
                            matchPoint
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    scoreCount: SnapshotStateList<Int>,
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
            Text(text = "Reset Score", textAlign = TextAlign.Center)
        }
        Box(Modifier.size(10.dp))
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
            Text(text = "Reset ALL", textAlign = TextAlign.Center)
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    VolleyballApp()
}