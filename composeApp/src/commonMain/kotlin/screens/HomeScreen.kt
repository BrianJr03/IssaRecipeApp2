package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.ShareDialog
import constants.APP_NAME
import constants.ASK
import constants.FAVORITES
import constants.GENERATE
import constants.HomeConstants
import constants.MENU
import constants.SETTINGS
import constants.SHARE
import navigation.homeScreen.HomeScreenComponent
import navigation.homeScreen.HomeScreenEvent

@Composable
fun HomeScreen(component: HomeScreenComponent) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        with(component) {
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
            OptionCardRow()
        }
    }
}

@Composable
private fun HomeScreenComponent.HeaderRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            top = HomeConstants.HEADER_ROW_PADDING_TOP,
            bottom = HomeConstants.HEADER_ROW_PADDING_BOTTOM,
            start = HomeConstants.HOME_PADDING_START,
            end = HomeConstants.HOME_PADDING_END
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
private fun HomeScreenComponent.OptionCardRow() {
    LazyRow {
        item {
            OptionCard(
                text = ASK,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnAskClick
                    )
                }
            )
            OptionCard(
                text = GENERATE,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnGenerateClick
                    )
                }
            )
            OptionCard(
                text = FAVORITES,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnFavoritesClick
                    )
                }
            )
            OptionCard(
                text = SETTINGS,
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnSettingsClick
                    )
                }
            )
        }
    }
}

@Composable
private fun OptionCard(
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(HomeConstants.OPTION_CARD_HEIGHT)
            .padding(
                start = HomeConstants.HOME_PADDING_START,
                end = HomeConstants.OPTION_CARD_PADDING_END
            )
            .clickable { onClick() },
        elevation = HomeConstants.OPTION_CARD_ELEVATION
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    style = TextStyle(fontSize = HomeConstants.OPTION_CARD_TEXT_FONT_SIZE),
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = HomeConstants.OPTION_CARD_TEXT_PADDING_START,
                        end = HomeConstants.OPTION_CARD_TEXT_PADDING_END
                    )
                )
            }
        }
    }
}