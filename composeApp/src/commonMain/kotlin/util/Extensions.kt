package util

import androidx.compose.ui.graphics.Color
import constants.GOLD
import constants.GREEN

fun String.getRatingBoxColor(): Color {
    val double = this.toDoubleOrNull()
    double?.let {
        return when (it) {
            in 0.0..2.99 -> Color.Red
            in 3.0..3.99 -> GOLD
            else -> GREEN
        }
    }
    return Color.Transparent
}

fun String.validateIngredients() = ifBlank { "Any" }
fun String.validatePartySize() = ifBlank { "1" }
fun String.validateOccasion() = ifBlank { "Any" }
fun String.validateDietary() = ifBlank { "None" }
fun String.validateAllergies() = ifBlank { "None" }
fun String.validateOtherInfo() = ifBlank { "" }