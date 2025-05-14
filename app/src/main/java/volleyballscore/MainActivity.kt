package volleyballscore

import android.R.style.Theme_DeviceDefault
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.PageIndicatorState
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices
import volleyballscore.theme.VolleyballScoreTheme
import volleyballscore.screens.MainScreen
import volleyballscore.screens.OptionsScreen

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
    val scoreCount = remember { mutableStateListOf(listOf(0, 0)) }
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

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    VolleyballApp()
}