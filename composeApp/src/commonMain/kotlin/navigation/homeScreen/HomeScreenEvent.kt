package navigation.homeScreen

sealed interface HomeScreenEvent {
    data object OnAskClick: HomeScreenEvent
    data object OnGenerateClick: HomeScreenEvent
    data object OnSettingsClick: HomeScreenEvent
}