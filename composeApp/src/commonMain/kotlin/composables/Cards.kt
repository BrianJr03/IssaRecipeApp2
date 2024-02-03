package composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import constants.cards.*
import constants.home.HOME_PADDING_START

@Composable
fun RecentRecipeCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(RECIPE_CARD_HEIGHT)
            .width(RECIPE_CARD_WIDTH)
            .padding(
                start = HOME_PADDING_START,
                end = RECIPE_CARD_PADDING_END
            )
            .clickable { onClick() },
        elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "text",
                    style = TextStyle(fontSize = RECIPE_CARD_TEXT_FONT_SIZE),
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = RECIPE_CARD_TEXT_PADDING_START,
                        end = RECIPE_CARD_TEXT_PADDING_END
                    )
                )
            }
        }
    }
}

@Composable
fun OptionCard(
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(OPTION_CARD_HEIGHT)
            .padding(
                start = HOME_PADDING_START,
                end = OPTION_CARD_PADDING_END
            )
            .clickable { onClick() },
        elevation = CARD_ELEVATION
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
                    style = TextStyle(fontSize = OPTION_CARD_TEXT_FONT_SIZE),
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = OPTION_CARD_TEXT_PADDING_START,
                        end = OPTION_CARD_TEXT_PADDING_END
                    )
                )
            }
        }
    }
}