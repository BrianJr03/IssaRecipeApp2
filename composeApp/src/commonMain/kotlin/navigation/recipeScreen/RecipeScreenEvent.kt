package navigation.recipeScreen

sealed interface RecipeScreenEvent {
    data object OnNavBack: RecipeScreenEvent
}