package navigation.favoritesScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class FavsScreenComponent(
    componentContext: ComponentContext,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: FavsScreenEvent) {
        when (event) {
            FavsScreenEvent.OnNavBack -> {
                onNavBack()
            }
        }
    }
}