package blocs.generateScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import blocs.generateScreen.GenerateScreenEvent
import models.local.Recipe

class GenerateScreenComponent(
    componentContext: ComponentContext,
    private val onGenerateRecipe: (Recipe) -> Unit,
    private val onRandomizeRecipe: (Recipe) -> Unit,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: GenerateScreenEvent) {
        when (event) {
            is GenerateScreenEvent.OnGenerateRecipe -> onGenerateRecipe(event.recipe)
            is GenerateScreenEvent.OnRandomizeRecipe -> onRandomizeRecipe(event.recipe)
            is GenerateScreenEvent.OnNavBack -> onNavBack()
        }
    }
}