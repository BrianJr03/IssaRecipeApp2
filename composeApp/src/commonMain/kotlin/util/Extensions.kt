package util

import androidx.compose.ui.graphics.Color

fun String.getRatingBoxColor(): Color {
    val double = this.toDoubleOrNull()
    double?.let {
        return when (it) {
            in 0.0..2.99 -> Color.Red
            in 3.0..3.99 -> Color.Yellow
            else -> Color.Green
        }
    }
    return Color.Transparent
}