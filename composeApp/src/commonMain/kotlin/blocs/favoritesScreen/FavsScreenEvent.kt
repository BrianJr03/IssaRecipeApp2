package blocs.favoritesScreen

import models.local.Recipe

sealed interface FavsScreenEvent {
    data object OnNavBack: FavsScreenEvent
    data class OnRecipeClick(
        val recipe: Recipe
    ): FavsScreenEvent
}