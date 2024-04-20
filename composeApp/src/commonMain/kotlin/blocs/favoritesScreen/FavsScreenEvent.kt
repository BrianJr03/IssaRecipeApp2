package blocs.favoritesScreen

sealed interface FavsScreenEvent {
    data object OnNavBack: FavsScreenEvent
    data object OnRecipeClick: FavsScreenEvent
}