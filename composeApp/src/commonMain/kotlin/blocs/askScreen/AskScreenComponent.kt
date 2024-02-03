package blocs.askScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class AskScreenComponent(
    componentContext: ComponentContext,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: AskScreenEvent) {
        when (event) {
            AskScreenEvent.OnNavBack -> {
                onNavBack()
            }
            AskScreenEvent.OnSendClick -> TODO()
        }
    }
}