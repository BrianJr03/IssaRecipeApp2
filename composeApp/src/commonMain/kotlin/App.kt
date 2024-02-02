import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.RootComponent
import screens.AskScreen
import screens.GenerateScreen
import screens.HomeScreen
import screens.SettingsScreen

@Composable
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.HomeScreen -> HomeScreen(instance.component)
                is RootComponent.Child.AskScreen -> AskScreen(instance.component)
                is RootComponent.Child.GenerateScreen -> GenerateScreen(instance.component)
                is RootComponent.Child.SettingsScreen -> SettingsScreen(instance.component)
            }
        }
    }
}