package navigation.screenB

import com.arkivanov.decompose.ComponentContext

class ScreenBComponent(
    val text: String,
    componentContext: ComponentContext,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    fun goBack() {
        onNavBack()
    }

    fun onEvent(event: ScreenBEvent) {
        when (event) {
            is ScreenBEvent.ClickButtonB -> {

            }
            is ScreenBEvent.UpdateText -> {

            }
        }
    }
}