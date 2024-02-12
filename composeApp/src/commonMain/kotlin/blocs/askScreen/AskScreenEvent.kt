package blocs.askScreen

sealed interface AskScreenEvent {
    data object OnNavBack: AskScreenEvent
    data object OnSendClick: AskScreenEvent
    data object OnNavToSettings: AskScreenEvent
    data object OnNavToAskContext: AskScreenEvent
}