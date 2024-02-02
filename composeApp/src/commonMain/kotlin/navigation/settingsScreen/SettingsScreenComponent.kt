package navigation.settingsScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class SettingsScreenComponent(
    componentContext: ComponentContext,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: SettingsScreenEvent) {
        when (event) {
            SettingsScreenEvent.OnNavBack -> {
                onNavBack()
            }
            SettingsScreenEvent.OnSaveApiKey -> TODO()
        }
    }
}