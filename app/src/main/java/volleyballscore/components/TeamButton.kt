package volleyballscore.components

import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.material.Button

@Composable
fun TeamButton(
    modifier: Modifier,
    teamIndex: Int,
    scoreCount: SnapshotStateList<Int>,
    setCount: SnapshotStateList<Int>,
    serving: SnapshotStateList<Boolean>,
    matchPoint: MutableState<Boolean>
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

            val otherTeam = teamIndex xor 1;
            scoreCount[teamIndex]++;
            serving[teamIndex] = true;
            serving[otherTeam] = false;

            if (scoreCount[teamIndex] >= 25 &&
                (scoreCount[teamIndex] - scoreCount[otherTeam] >= 2)
            ) {
                scoreCount[teamIndex] = 0;
                setCount[teamIndex]++;
                scoreCount[otherTeam] = 0;
                serving[teamIndex] = false;
                serving[otherTeam] = false;
            }

            if ((scoreCount[teamIndex] >= 24 || scoreCount[otherTeam] >= 24) &&
                (scoreCount[teamIndex] != scoreCount[otherTeam])
            ) {
                matchPoint.value = true
            } else {
                matchPoint.value = false
            }
        },
        enabled = true,
    ) {}
}