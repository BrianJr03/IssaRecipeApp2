package blocs.generateScreen

import models.local.Recipe

sealed interface GenerateScreenEvent {
    data object OnNavBack: GenerateScreenEvent
    data class OnGenerateRecipe(
        val recipe: Recipe
    ): GenerateScreenEvent
    data class OnRandomizeRecipe(
        val recipe: Recipe
    ): GenerateScreenEvent
}