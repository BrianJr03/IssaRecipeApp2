package blocs.seeAllScreen

sealed interface SeeAllScreenEvent {
    data object OnNavBack: SeeAllScreenEvent
}