package constants

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

val DEFAULT_TEXT_STYLE = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontSize = 16.sp
)

fun defaultVerticalGradient(
    colors: List<Color> = listOf(STEEL_BLUE, WHEAT)
): Brush {
    return Brush.verticalGradient(
        colors
    )
}