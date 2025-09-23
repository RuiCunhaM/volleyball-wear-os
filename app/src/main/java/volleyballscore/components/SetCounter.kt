package volleyballscore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import volleyballscore.models.VolleyBallScoreViewModel
import volleyballscore.screens.HOME_TEAM
import volleyballscore.screens.VISITOR_TEAM

@Composable
fun SetCounter(
    modifier: Modifier,
    viewModel: VolleyBallScoreViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Right,
            fontSize = 25.sp,
            text = "${uiState.setCounter[HOME_TEAM]}"
        )
        Text(
            textAlign = TextAlign.Center,
            fontSize = 25.sp, text = " - "
        )
        Text(
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Left,
            fontSize = 25.sp,
            text = "${uiState.setCounter[VISITOR_TEAM]}"
        )
    }
}