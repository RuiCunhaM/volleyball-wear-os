package volleyballscore.components

import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import volleyballscore.models.VolleyBallScoreViewModel

@Composable
fun TeamButton(
    modifier: Modifier,
    teamIndex: Int,
    viewModel: VolleyBallScoreViewModel = viewModel()
) {
    // NOTE: The EFFECT_CLICK seems weak, and the EFFECT_HEAVY_CLICK does not seem compatible
    // with Galaxy Watch 4. Therefore, we set a custom click duration effect.
    val vibrator = LocalContext.current.getSystemService(Vibrator::class.java);
    val clickEffect = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE);

    Button(
        modifier = modifier
            .alpha(0.0f)
            .fillMaxSize(),
        shape = RectangleShape,
        onClick = {
            vibrator.vibrate(clickEffect);
            viewModel.score(teamIndex)
        },
        enabled = true,
    ) {}
}