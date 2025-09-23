package volleyballscore.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Icon
import com.example.volleyballscore.R
import volleyballscore.models.VolleyBallScoreViewModel

@Composable
fun ServingIcon(
    teamIndex: Int,
    viewModel: VolleyBallScoreViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isServing[teamIndex])
        Icon(
            modifier = Modifier.size(15.dp),
            painter = painterResource(id = R.drawable.logo_white),
            contentDescription = "$teamIndex is Serving"
        )
    else
        Box(Modifier.size(15.dp))
}