package blocs.recipeScreen

import com.arkivanov.decompose.ComponentContext
import models.local.RecentRecipe

class RecipeScreenComponent(
    componentContext: ComponentContext,
    val recipe: RecentRecipe,
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