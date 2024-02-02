package navigation.homeScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val onNavToAsk: () -> Unit,
    private val onNavToGenerate: () -> Unit,
    private val onNavToFavorites: () -> Unit,
    private val onNavToSettings: () -> Unit
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnAskClick -> onNavToAsk()
            is HomeScreenEvent.OnGenerateClick -> onNavToGenerate()
            is HomeScreenEvent.OnFavoritesClick -> onNavToFavorites()
            is HomeScreenEvent.OnSettingsClick -> onNavToSettings()
        }
    }
}