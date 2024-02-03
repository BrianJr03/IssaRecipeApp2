package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import model.local.RecentRecipe
import navigation.askScreen.AskScreenComponent
import navigation.favoritesScreen.FavsScreenComponent
import navigation.askScreen.GenerateScreenComponent
import navigation.settingsScreen.SettingsScreenComponent
import navigation.homeScreen.HomeScreenComponent
import navigation.recipeScreen.RecipeScreenComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    val childStack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.HomeScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Config,
        context: ComponentContext
    ): Child {
        return when (config) {
            is Config.HomeScreen -> {
                Child.HomeScreen(
                    HomeScreenComponent(
                        componentContext = context,
                        onNavToAsk = {
                            navigation.pushNew(Config.AskScreen)
                        },
                        onNavToGenerate = {
                            navigation.pushNew(Config.GenerateScreen)
                        },
                        onNavToFavorites = {
                            navigation.pushNew(Config.FavoritesScreen)
                        },
                        onNavToSettings = {
                            navigation.pushNew(Config.Settings)
                        },
                        onNavToRecipePage = {
                            navigation.pushNew(Config.RecipeScreen(it))
                            // TODO - FIND WAY TO SHOW RECENT RECIPE IN SCREEN
                        }
                    )
                )
            }

            is Config.AskScreen -> {
                Child.AskScreen(
                    AskScreenComponent(
                        componentContext = context,
                        onNavBack = { navigation.pop() }
                    )
                )
            }

            is Config.FavoritesScreen -> {
                Child.FavoritesScreen(
                    FavsScreenComponent(
                        componentContext = context,
                        onNavBack = { navigation.pop() }
                    )
                )
            }

            is Config.GenerateScreen -> {
                Child.GenerateScreen(
                    GenerateScreenComponent(
                        componentContext = context,
                        onNavBack = { navigation.pop() }
                    )
                )
            }

            is Config.RecipeScreen -> {
                Child.RecipeScreen(
                    RecipeScreenComponent(
                        componentContext = context,
                        recipe = config.recentRecipe,
                        onNavBack = { navigation.pop() }
                    )
                )
            }

            is Config.Settings -> {
                Child.SettingsScreen(
                    SettingsScreenComponent(
                        componentContext = context,
                        onNavBack = { navigation.pop() }
                    )
                )
            }
        }
    }

    sealed class Child {
        data class HomeScreen(val component: HomeScreenComponent) : Child()
        data class AskScreen(val component: AskScreenComponent) : Child()
        data class RecipeScreen(val component: RecipeScreenComponent) : Child()
        data class GenerateScreen(val component: GenerateScreenComponent) : Child()
        data class FavoritesScreen(val component: FavsScreenComponent) : Child()
        data class SettingsScreen(val component: SettingsScreenComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object HomeScreen : Config()
        @Serializable
        data object AskScreen : Config()
        @Serializable
        data class RecipeScreen(val recentRecipe: RecentRecipe): Config()
        @Serializable
        data object GenerateScreen : Config()
        @Serializable
        data object FavoritesScreen : Config()
        @Serializable
        data object Settings : Config()
    }
}