package util

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import constants.DEFAULT_RECIPE_TITLE

fun String.getRatingBoxColor(): Color {
    val double = this.toDoubleOrNull()
    double?.let {
        return when (it) {
            in 0.0..2.99 -> Color.Red
            in 3.0..3.99 -> COLOR_GOLD
            else -> COLOR_GREEN
        }
    }
    return Color.Transparent
}

fun String.validateIngredients() = ifBlank { "Provide random ingredients." }
fun String.validatePartySize() = ifBlank { "1" }
fun String.validateOccasion() = ifBlank {
    listOf(
        "Breakfast",
        "Brunch",
        "Lunch",
        "Dinner",
        "Dessert"
    ).random()
}

fun String.validateDietary() = ifBlank { "None" }
fun String.validateAllergies() = ifBlank { "None" }
fun String.validateOtherInfo() = ifBlank { "" }

fun String.extractRecipeTitle(): String {
    val regex = Regex("""✨(.*?)✨""")
    val matchResult = regex.find(this)
    return matchResult?.groupValues?.get(1)?.trim() ?: DEFAULT_RECIPE_TITLE
}

fun MutableState<Boolean>.negate() {
    value = !value
}