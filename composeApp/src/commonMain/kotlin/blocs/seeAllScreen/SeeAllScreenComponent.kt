package blocs.seeAllScreen

import com.arkivanov.decompose.ComponentContext

class SeeAllScreenComponent(
    componentContext: ComponentContext,
    private val onNavBack: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: SeeAllScreenEvent) {
        when (event) {
            SeeAllScreenEvent.OnNavBack -> {
                onNavBack()
            }
        }
    }
}