package navigation.recipeScreen

import com.arkivanov.decompose.ComponentContext
import model.local.RecentRecipe

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