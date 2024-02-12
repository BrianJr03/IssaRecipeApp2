package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import composables.ShareDialog
import constants.APP_NAME
import constants.ASK
import constants.FAVORITES
import constants.GENERATE
import constants.home.*
import constants.MENU
import constants.RECENT_RECIPES
import constants.SETTINGS
import constants.SHARE
import models.local.TEST_RECENT_RECIPES
import blocs.homeScreen.HomeScreenComponent
import blocs.homeScreen.HomeScreenEvent
import composables.ArrowIcon
import composables.OptionCard
import composables.VerticalRecipeCard
import composables.HorizontalRecipeCard
import composables.SeeAllCard
import constants.YOU_GOTTA_TRY_THIS

@Composable
fun HomeScreenComponent.HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
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

            HeaderRow()
            ScreenOptionCardRow()
            RecipesRow(rowLabel = RECENT_RECIPES)
        }

        item {
            Text(
                YOU_GOTTA_TRY_THIS,
                modifier = Modifier.padding(
                    start = HOME_PADDING_START,
                    bottom = ROW_LABEL_PADDING_BOTTOM
                )
            )
        }

        items(TEST_RECENT_RECIPES.take(5).size) {
            HorizontalRecipeCard(TEST_RECENT_RECIPES[it]) {

            }
        }

        item {
            SeeAllCard {

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
            start = HOME_PADDING_START,
            end = HOME_PADDING_END
        )
    ) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = MENU,
        )

        Spacer(Modifier.weight(1f))

        Text(APP_NAME)

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = Icons.Rounded.Share,
            contentDescription = SHARE,
            modifier = Modifier.clickable {
                onEvent(
                    HomeScreenEvent.OnShareClick
                )
            }
        )
    }
}

@Composable
private fun HomeScreenComponent.ScreenOptionCardRow() {
    LazyRow {
        item {
            OptionCard(
                text = ASK,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnAskClick
                    )
                },
                trailingIcon = {
                    ArrowIcon()
                }
            )
            OptionCard(
                text = GENERATE,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnGenerateClick
                    )
                },
                trailingIcon = {
                    ArrowIcon()
                }
            )
            OptionCard(
                text = FAVORITES,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnFavoritesClick
                    )
                },
                trailingIcon = {
                    ArrowIcon()
                }
            )
            OptionCard(
                text = SETTINGS,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnSettingsClick
                    )
                },
                trailingIcon = {
                    ArrowIcon()
                }
            )
        }
    }
}

@Composable
private fun HomeScreenComponent.RecipesRow(
    rowLabel: String
) {
    Column(modifier = Modifier.padding(bottom = SECTION_PADDING_BOTTOM)) {
        Text(
            rowLabel,
            modifier = Modifier.padding(
                start = HOME_PADDING_START,
                top = ROW_LABEL_PADDING_TOP,
                bottom = ROW_LABEL_PADDING_BOTTOM
            )
        )
        Spacer(Modifier.height(5.dp))
        LazyRow {
            items(TEST_RECENT_RECIPES.takeLast(7).size) { index ->
                VerticalRecipeCard(TEST_RECENT_RECIPES[index]) {
                    onEvent(
                        HomeScreenEvent.OnRecentRecipeClick(TEST_RECENT_RECIPES[index])
                    )
                }
            }
        }
    }
}