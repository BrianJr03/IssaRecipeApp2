package blocs.recipeScreen

sealed interface RecipeScreenEvent {
    data object OnNavBack: RecipeScreenEvent
}