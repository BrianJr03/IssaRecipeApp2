package navigation.homeScreen

import model.local.RecentRecipe

sealed interface HomeScreenEvent {
    data class OnRecentRecipeClick(
        val recentRecipe: RecentRecipe
    ): HomeScreenEvent

    data object OnAskClick: HomeScreenEvent
    data object OnShareClick: HomeScreenEvent
    data object OnGenerateClick: HomeScreenEvent
    data object OnFavoritesClick: HomeScreenEvent
    data object OnSettingsClick: HomeScreenEvent
}