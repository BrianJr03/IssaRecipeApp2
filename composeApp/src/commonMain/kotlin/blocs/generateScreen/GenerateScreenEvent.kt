package blocs.generateScreen

sealed interface GenerateScreenEvent {
    data object OnNavBack: GenerateScreenEvent
    data object OnGenerateRecipe: GenerateScreenEvent
    data object OnRandomizeRecipe: GenerateScreenEvent
}