package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import navigation.homeScreen.HomeScreenComponent
import navigation.screenB.ScreenBComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    val childStack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.ScreenA,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Config,
        context: ComponentContext
    ): Child {
        return when (config) {
            is Config.ScreenA -> Child.ScreenA(
                HomeScreenComponent(
                    componentContext = context,
                    onNavToScreenB = {
                        navigation.pushNew(Config.ScreenB(it))
                    }
                )
            )

            is Config.ScreenB -> Child.ScreenB(
                ScreenBComponent(
                    text = config.text,
                    componentContext = context,
                    onNavBack = {
                        navigation.pop()
                    }
                )
            )
        }
    }

    sealed class Child {
        data class ScreenA(val component: HomeScreenComponent) : Child()
        data class ScreenB(val component: ScreenBComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object ScreenA : Config()

        @Serializable
        data class ScreenB(val text: String) : Config()
    }
}