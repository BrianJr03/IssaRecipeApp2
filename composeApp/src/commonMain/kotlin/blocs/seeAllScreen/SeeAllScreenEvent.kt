package blocs.seeAllScreen

import models.local.Recipe

sealed interface SeeAllScreenEvent {
    data class OnRecentRecipeClick(
        val recipe: Recipe
    ): SeeAllScreenEvent
    data object OnNavBack: SeeAllScreenEvent
}