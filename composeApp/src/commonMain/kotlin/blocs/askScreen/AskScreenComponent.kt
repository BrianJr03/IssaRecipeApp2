package blocs.askScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class AskScreenComponent(
    componentContext: ComponentContext,
    private val onNavBack: () -> Unit,
    private val onNavToAskContext: () -> Unit,
    private val onNavToSettings: () -> Unit,
) : ComponentContext by componentContext {

    private var _savedApiKey = MutableValue("")
    val savedApiKey: Value<String> = _savedApiKey

    private var _savedAskContext = MutableValue("")
    val savedAskContext: Value<String> = _savedAskContext

    private var _savedModel = MutableValue("")
    val savedModel: Value<String> = _savedModel

    fun onEvent(event: AskScreenEvent) {
        when (event) {
            AskScreenEvent.OnNavBack -> onNavBack()
            AskScreenEvent.OnSendClick -> TODO()
            AskScreenEvent.OnNavToAskContext -> onNavToAskContext()
            AskScreenEvent.OnNavToSettings -> onNavToSettings()
        }
    }
}