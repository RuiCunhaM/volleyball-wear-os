package com.example.volleyballscore.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.CurvedAlignment
import androidx.wear.compose.foundation.CurvedDirection
import androidx.wear.compose.foundation.CurvedLayout
import androidx.wear.compose.foundation.CurvedModifier
import androidx.wear.compose.foundation.curvedColumn
import androidx.wear.compose.foundation.curvedRow
import androidx.wear.compose.foundation.padding
import androidx.wear.compose.material.curvedText

@Composable
fun TeamsNames() {
    CurvedLayout(anchor = 90f, angularDirection = CurvedDirection.Angular.Reversed) {
        curvedColumn(
            angularAlignment = CurvedAlignment.Angular.Center
        ) {
            curvedRow {
                curvedText(
                    modifier = CurvedModifier.padding(angular = 50.dp),
                    text = "Home"
                    // text = if (serving[0]) "\uD83C\uDFD0 Home" else "Home"
                )
                curvedText(
                    modifier = CurvedModifier.padding(angular = 50.dp),
                    text = "Visitor"
                    // text = if (serving[1]) "Visitor \uD83C\uDFD0" else "Visitor"
                )
            }
        }
    }
}