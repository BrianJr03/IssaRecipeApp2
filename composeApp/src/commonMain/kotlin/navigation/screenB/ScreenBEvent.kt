package navigation.screenB

sealed interface ScreenBEvent {
    data object ClickButtonB: ScreenBEvent
    data class UpdateText(val text: String): ScreenBEvent
}