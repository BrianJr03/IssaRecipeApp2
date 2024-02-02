package navigation.homeScreen

sealed interface HomeScreenEvent {
    data object ClickButtonHome: HomeScreenEvent
    data class UpdateText(val text: String): HomeScreenEvent
}