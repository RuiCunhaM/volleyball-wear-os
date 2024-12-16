package com.example.volleyballscore.presentation

import android.R.style.Theme_DeviceDefault
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.volleyballscore.presentation.theme.VolleyballScoreTheme

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

@Composable
fun VolleyballApp() {
    val scoreCount = remember { mutableStateListOf(0, 0) }
    val setCount = remember { mutableStateListOf(0, 0) }
    // val serving = remember { mutableStateListOf(false, false) }
    val matchPoint = remember { mutableStateOf(false) }

    VolleyballScoreTheme {
        // Score and Set counters
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
            SetCounter(
                modifier = Modifier,
                setCount
            )
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
                // serving,
                matchPoint
            )
            TeamButton(
                Modifier.weight(1f),
                1,
                scoreCount,
                setCount,
                // serving,
                matchPoint
            )
        }

        // Time
        TimeText(timeTextStyle = TextStyle(color = Color.White))

        // Bottom text
        TeamsNames()
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    VolleyballApp()
}