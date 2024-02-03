package navigation.homeScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import model.local.RecentRecipe

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val onNavToAsk: () -> Unit,
    private val onNavToGenerate: () -> Unit,
    private val onNavToFavorites: () -> Unit,
    private val onNavToSettings: () -> Unit,
    private val onNavToRecipePage: (RecentRecipe) -> Unit
) : ComponentContext by componentContext {
    private var _isShareDialogShowing = MutableValue(false)
    val isShareDialogShowing: Value<Boolean> = _isShareDialogShowing

    private fun showShareDialog() {
        _isShareDialogShowing.value = true
    }

    fun hideShareDialog() {
        _isShareDialogShowing.value = false
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnAskClick -> onNavToAsk()
            is HomeScreenEvent.OnGenerateClick -> onNavToGenerate()
            is HomeScreenEvent.OnFavoritesClick -> onNavToFavorites()
            is HomeScreenEvent.OnSettingsClick -> onNavToSettings()
            is HomeScreenEvent.OnShareClick -> showShareDialog()
            is HomeScreenEvent.OnRecentRecipeClick -> onNavToRecipePage(event.recentRecipe)
        }
    }
}