package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.composables.ShareDialog
import constants.APP_NAME
import constants.ASK
import constants.FAVORITES
import constants.GENERATE
import constants.MENU
import constants.RECENT_RECIPES
import constants.SETTINGS
import models.local.TEST_RECENT_RECIPES
import blocs.homeScreen.HomeScreenComponent
import blocs.homeScreen.HomeScreenEvent
import util.DEFAULT_TEXT_STYLE
import ui.composables.CardButton
import ui.composables.VerticalRecipeCard
import ui.composables.HorizontalRecipeCard
import ui.composables.SeeAllCard
import constants.YOU_GOTTA_TRY_THIS
import util.cards.HORIZONTAL_CARD_HEIGHT
import util.cards.MAX_RECENT_RECIPE_COUNT
import util.cards.OPTION_CARD_PADDING_END
import util.cards.RECIPE_CARD_HEIGHT
import util.cards.RECIPE_CARD_WIDTH
import util.cards.SEE_ALL_CARD_HEIGHT
import util.cards.SEE_ALL_CARD_PADDING_BOTTOM
import util.cards.SEE_ALL_CARD_PADDING_END
import util.getCardInListColor
import util.defaultVerticalGradient
import models.local.Recipe
import models.local.SqlDataSourceImpl
import models.local.toRecipe
import util.home.DEFAULT_PADDING_END
import util.home.DEFAULT_PADDING_START
import util.home.HEADER_ROW_PADDING_BOTTOM
import util.home.HEADER_ROW_PADDING_TOP
import util.home.ROW_LABEL_PADDING_BOTTOM
import util.home.ROW_LABEL_PADDING_TOP
import util.home.SECTION_PADDING_BOTTOM

@Composable
fun HomeScreenComponent.HomeScreen(
    sqlDataSourceImpl: SqlDataSourceImpl
) {
    val recentRecipes = rememberSaveable { mutableStateOf(listOf<Recipe>()) }

    LaunchedEffect(Unit) {
        sqlDataSourceImpl.recipes.collect {
            if (it.size > MAX_RECENT_RECIPE_COUNT) {
                sqlDataSourceImpl.deleteWithId(it.first().content)
            }
            recentRecipes.value = it.map { sqlRecipe -> sqlRecipe.toRecipe() }
        }
    }

    Scaffold(
        topBar = {
            HeaderRow()
        }
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                val isShareShowing = isShareDialogShowing.subscribeAsState()

                ShareDialog(
                    isShowing = isShareShowing.value,
                    onConfirmClick = {
                        hideShareDialog()
                    },
                    onDismissRequest = {
                        hideShareDialog()
                    }
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                ScreenOptionCardRow()
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                RecentRecipesRow(
                    rowLabel = RECENT_RECIPES,
                    list = recentRecipes.value
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                Text(
                    YOU_GOTTA_TRY_THIS,
                    style = DEFAULT_TEXT_STYLE,
                    modifier = Modifier.padding(
                        start = DEFAULT_PADDING_START,
                        bottom = ROW_LABEL_PADDING_BOTTOM
                    )
                )
            }

            items(TEST_RECENT_RECIPES.take(4).size) { index ->
                HorizontalRecipeCard(
                    TEST_RECENT_RECIPES[index],
                    modifier = Modifier
                        .height(HORIZONTAL_CARD_HEIGHT)
                        .padding(
                            start = DEFAULT_PADDING_START,
                            bottom = 10.dp,
                            end = OPTION_CARD_PADDING_END
                        ),
                    color = getCardInListColor(index)

                ) {
                    onEvent(
                        HomeScreenEvent.OnRecentRecipeClick(TEST_RECENT_RECIPES[index])
                    )
                }
            }

            item {
                SeeAllCard(
                    modifier = Modifier
                        .height(SEE_ALL_CARD_HEIGHT)
                        .fillMaxWidth()
                        .padding(
                            start = DEFAULT_PADDING_START,
                            bottom = SEE_ALL_CARD_PADDING_BOTTOM,
                            end = SEE_ALL_CARD_PADDING_END
                        ).clickable {
                            onEvent(
                                HomeScreenEvent.OnSeeAllClick
                            )
                        },
                    boxModifier = Modifier.background(
                        defaultVerticalGradient()
                    )
                )
            }
        }
    }
}

@Composable
private fun HomeScreenComponent.HeaderRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            top = HEADER_ROW_PADDING_TOP,
            bottom = HEADER_ROW_PADDING_BOTTOM,
            start = DEFAULT_PADDING_START,
            end = DEFAULT_PADDING_END
        )
    ) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = MENU,
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = APP_NAME,
            style = DEFAULT_TEXT_STYLE
        )

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = Icons.Rounded.Settings,
            contentDescription = SETTINGS,
            modifier = Modifier.clickable {
                onEvent(
                    HomeScreenEvent.OnSettingsClick
                )
            }
        )
    }
}

@Composable
private fun HomeScreenComponent.ScreenOptionCardRow() {
    Row {
        Spacer(Modifier.weight(1f))
        CardButton(
            text = ASK,
            boxModifier = Modifier
                .background(defaultVerticalGradient()).border(
                    width = 1.dp,
                    color = Color.Black
                ),
            onClick = {
                onEvent(
                    HomeScreenEvent.OnAskClick
                )
            }
        )
        CardButton(
            text = GENERATE,
            boxModifier = Modifier.background(defaultVerticalGradient()).border(
                width = 1.dp,
                color = Color.Black
            ),
            onClick = {
                onEvent(
                    HomeScreenEvent.OnGenerateClick
                )
            }
        )
        CardButton(
            text = FAVORITES,
            boxModifier = Modifier.background(defaultVerticalGradient()).border(
                width = 1.dp,
                color = Color.Black
            ),
            onClick = {
                onEvent(
                    HomeScreenEvent.OnFavoritesClick
                )
            }
        )
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun HomeScreenComponent.RecentRecipesRow(
    rowLabel: String,
    list: List<Recipe>
) {
    val reversed = list.reversed()

    Column(modifier = Modifier.padding(bottom = SECTION_PADDING_BOTTOM)) {
        Text(
            rowLabel,
            style = DEFAULT_TEXT_STYLE,
            modifier = Modifier.padding(
                start = DEFAULT_PADDING_START,
                top = ROW_LABEL_PADDING_TOP,
                bottom = ROW_LABEL_PADDING_BOTTOM
            )
        )
        Spacer(Modifier.height(5.dp))
        LazyRow {
            items(reversed.take(MAX_RECENT_RECIPE_COUNT).size) {
                VerticalRecipeCard(
                    color = getCardInListColor(it),
                    recipe = reversed[it],
                    modifier = Modifier
                        .height(RECIPE_CARD_HEIGHT)
                        .width(RECIPE_CARD_WIDTH)
                ) {
                    onEvent(
                        HomeScreenEvent.OnRecentRecipeClick(reversed[it])
                    )
                }
            }
        }
    }
}