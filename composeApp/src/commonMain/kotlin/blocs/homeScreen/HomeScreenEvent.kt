package blocs.homeScreen

import models.local.Recipe

sealed interface HomeScreenEvent {
    data class OnRecentRecipeClick(
        val recipe: Recipe
    ): HomeScreenEvent

    data object OnAskClick: HomeScreenEvent
    data object OnShareClick: HomeScreenEvent
    data object OnGenerateClick: HomeScreenEvent
    data object OnFavoritesClick: HomeScreenEvent
    data object OnSettingsClick: HomeScreenEvent
}