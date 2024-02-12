package blocs.recipeScreen

import com.arkivanov.decompose.ComponentContext
import models.local.Recipe

class RecipeScreenComponent(
    componentContext: ComponentContext,
    val recipe: Recipe,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: RecipeScreenEvent) {
        when (event) {
            RecipeScreenEvent.OnNavBack -> {
                onNavBack()
            }
        }
    }
}