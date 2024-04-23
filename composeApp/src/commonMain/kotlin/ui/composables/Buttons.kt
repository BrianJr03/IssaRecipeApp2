package ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import util.defaultVerticalGradient

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black
            )
            .clip(RectangleShape)
            .background(defaultVerticalGradient()),
        onClick = {
            onClick()
        },
        content = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = Icons.Rounded.ArrowBack.name
            )
        }
    )
}