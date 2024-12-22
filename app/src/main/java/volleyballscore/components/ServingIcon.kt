package volleyballscore.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.example.volleyballscore.R

@Composable
fun ServingIcon(serving: SnapshotStateList<Boolean>, teamIndex: Int) {
    if (serving[teamIndex])
        Icon(
            modifier = Modifier.size(15.dp),
            painter = painterResource(id = R.drawable.logo_white),
            contentDescription = "$teamIndex is Serving"
        )
    else
        Box(Modifier.size(15.dp))
}