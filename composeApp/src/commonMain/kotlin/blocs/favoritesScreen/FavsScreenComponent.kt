package blocs.favoritesScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import models.local.Recipe

class FavsScreenComponent(
    componentContext: ComponentContext,
    private val onNavToRecipePage: (Recipe) -> Unit,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text
    fun onEvent(event: FavsScreenEvent) {
        when (event) {
            is FavsScreenEvent.OnNavBack -> {
                onNavBack()
            }
            is FavsScreenEvent.OnRecipeClick -> {
                onNavToRecipePage(event.recipe)
            }
        }
    }
}