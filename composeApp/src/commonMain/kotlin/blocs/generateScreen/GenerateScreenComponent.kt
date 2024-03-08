package blocs.askScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import blocs.generateScreen.GenerateScreenEvent

class GenerateScreenComponent(
    componentContext: ComponentContext,
    private val onGenerateRecipe: () -> Unit,
    private val onRandomizeRecipe: () -> Unit,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: GenerateScreenEvent) {
        when (event) {
            GenerateScreenEvent.OnGenerateRecipe -> {
                onGenerateRecipe()
            }
            GenerateScreenEvent.OnRandomizeRecipe -> {
                onRandomizeRecipe()
            }
            GenerateScreenEvent.OnNavBack -> {
                onNavBack()
            }
        }
    }
}