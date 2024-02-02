package navigation.askScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import navigation.generateScreen.GenerateScreenEvent

class GenerateScreenComponent(
    componentContext: ComponentContext,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: GenerateScreenEvent) {
        when (event) {
            GenerateScreenEvent.OnGenerateRecipe -> TODO()
            GenerateScreenEvent.OnRandomizeRecipe -> TODO()
            GenerateScreenEvent.OnNavBack -> {
                onNavBack()
            }
        }
    }
}