package blocs.seeAllScreen

import com.arkivanov.decompose.ComponentContext
import models.local.Recipe

class SeeAllScreenComponent(
    componentContext: ComponentContext,
    private val onNavToRecipePage: (Recipe) -> Unit,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: SeeAllScreenEvent) {
        when (event) {
            is SeeAllScreenEvent.OnRecentRecipeClick -> onNavToRecipePage(event.recipe)
            is SeeAllScreenEvent.OnNavBack -> onNavBack()
        }
    }
}