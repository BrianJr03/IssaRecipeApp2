package navigation.homeScreen

sealed interface HomeScreenEvent {
    data object OnAskClick: HomeScreenEvent
    data object OnShareClick: HomeScreenEvent
    data object OnGenerateClick: HomeScreenEvent
    data object OnFavoritesClick: HomeScreenEvent
    data object OnSettingsClick: HomeScreenEvent
}