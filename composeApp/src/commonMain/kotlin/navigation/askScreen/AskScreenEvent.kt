package navigation.askScreen

sealed interface AskScreenEvent {
    data object OnNavBack: AskScreenEvent
    data object OnSendClick: AskScreenEvent
}