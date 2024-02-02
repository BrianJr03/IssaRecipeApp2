package navigation.homeScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val onNavToScreenB: (String) -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ClickButtonHome -> onNavToScreenB(_text.value)
            is HomeScreenEvent.UpdateText -> {
                _text.value = event.text
            }
        }
    }
}