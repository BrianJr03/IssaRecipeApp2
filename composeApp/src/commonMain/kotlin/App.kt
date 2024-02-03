import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import blocs.RootComponent
import screens.*

@Composable
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.HomeScreen -> instance.component.HomeScreen()
                is RootComponent.Child.AskScreen -> instance.component.AskScreen()
                is RootComponent.Child.GenerateScreen -> instance.component.GenerateScreen()
                is RootComponent.Child.FavoritesScreen -> instance.component.FavoritesScreen()
                is RootComponent.Child.RecipeScreen -> {
                    instance.component.RecipeScreen(instance.component.recipe)
                }
                is RootComponent.Child.SettingsScreen -> instance.component.SettingsScreen()
            }
        }
    }
}