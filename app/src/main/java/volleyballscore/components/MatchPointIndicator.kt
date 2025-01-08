package volleyballscore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.volleyballscore.R

@Composable
fun MatchPointIndicator(matchPoint: MutableState<Boolean>) {
    if (matchPoint.value) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Match Point!")
            Icon(
                modifier = Modifier.size(21.dp),
                painter = painterResource(id = R.drawable.trophy),
                contentDescription = "Serving"
            )
        }
    } else Box(Modifier.height(21.dp))
}