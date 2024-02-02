package navigation.favoritesScreen

sealed interface FavsScreenEvent {
    data object OnNavBack: FavsScreenEvent
}