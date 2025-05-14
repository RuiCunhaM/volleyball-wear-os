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
    scoreCount: SnapshotStateList<List<Int>>,
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

            var currentScore = scoreCount.last().toMutableList();

            val otherTeam = teamIndex xor 1;
            currentScore[teamIndex]++;
            serving[teamIndex] = true;
            serving[otherTeam] = false;

            if (currentScore[teamIndex] >= 25 &&
                (currentScore[teamIndex] - currentScore[otherTeam] >= 2)
            ) {
                currentScore = mutableListOf(0, 0)
                scoreCount.clear();
                setCount[teamIndex]++;
                serving[teamIndex] = false;
                serving[otherTeam] = false;
            }

            matchPoint.value = (currentScore[teamIndex] >= 24 || currentScore[otherTeam] >= 24) &&
                    (currentScore[teamIndex] != currentScore[otherTeam])

            scoreCount.add(currentScore)
        },
        enabled = true,
    ) {}
}