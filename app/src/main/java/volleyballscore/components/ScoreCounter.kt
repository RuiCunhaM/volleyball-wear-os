package volleyballscore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

@Composable
fun ScoreCounter(modifier: Modifier, scoreCount: SnapshotStateList<List<Int>>) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Right,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            text = "${scoreCount.last()[0]}",
        )
        Text(
            textAlign = TextAlign.Center,
            fontSize = 50.sp, text = " - ",
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Left,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            text = "${scoreCount.last()[1]}",
        )
    }
}