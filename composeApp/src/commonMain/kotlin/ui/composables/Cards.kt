package ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import constants.BREAKFAST
import constants.DINNER
import constants.LUNCH
import constants.RECIPE_IMAGE
import constants.SEE_ALL
import constants.SNACKS
import constants.cards.*
import constants.home.HOME_PADDING_START
import models.local.Recipe
import util.getRatingBoxColor

@Composable
fun VerticalRecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.height(RECIPE_CARD_HEIGHT).width(RECIPE_CARD_WIDTH).padding(
            start = HOME_PADDING_START, end = RECIPE_CARD_PADDING_END
        ).clickable { onClick() }, elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = Modifier, contentAlignment = Alignment.TopCenter
        ) {
            Column {
                val painter = rememberImagePainter(url = recipe.imageUrl)
                Image(
                    painter = painter,
                    modifier = Modifier.height(VERTICAL_RECIPE_CARD_IMAGE_HEIGHT).fillMaxWidth()
                        .padding(
                            top = V_RECIPE_CARD_IMAGE_PADDING_TOP,
                            bottom = V_RECIPE_CARD_IMAGE_PADDING_BOTTOM
                        ),
                    contentDescription = RECIPE_IMAGE
                )
                Text(
                    recipe.title, modifier = Modifier.padding(
                        start = V_RECIPE_CARD_CONTENT_PADDING_START,
                        bottom = V_RECIPE_CARD_CONTENT_PADDING_BOTTOM
                    )
                )
                Text(
                    recipe.courseType, modifier = Modifier.padding(
                        start = V_RECIPE_CARD_CONTENT_PADDING_START,
                        bottom = V_RECIPE_CARD_CONTENT_PADDING_BOTTOM
                    )
                )
                Row(
                    modifier = Modifier.padding(
                        start = V_RECIPE_CARD_CONTENT_PADDING_START,
                        bottom = V_RECIPE_CARD_CONTENT_PADDING_BOTTOM,
                        end = V_RECIPE_CARD_CONTENT_PADDING_END
                    )
                ) {
                    Text(recipe.duration)
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier.background(recipe.rating.getRatingBoxColor())
                            .width(V_RECIPE_CARD_RATING_BOX_HEIGHT),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            recipe.rating, color = Color.White, modifier = Modifier.padding(
                                start = V_RECIPE_CARD_RATING_BOX_PADDING_START,
                                end = V_RECIPE_CARD_RATING_BOX_PADDING_END
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OptionCard(
    text: String, onClick: () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = Modifier.height(OPTION_CARD_HEIGHT).padding(
            start = HOME_PADDING_START, end = OPTION_CARD_PADDING_END
        ).clickable { onClick() }, elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    style = TextStyle(fontSize = OPTION_CARD_TEXT_FONT_SIZE),
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = OPTION_CARD_TEXT_PADDING_START,
                        end = if (trailingIcon != null) OPTION_CARD_ICON_PADDING_END
                        else OPTION_CARD_TEXT_PADDING_END
                    )
                )
                trailingIcon?.invoke()
            }
        }
    }
}

@Composable
fun HorizontalRecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.height(100.dp).padding(
            start = HOME_PADDING_START, bottom = 10.dp, end = OPTION_CARD_PADDING_END
        ).clickable { onClick() }, elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            val painter = rememberImagePainter(url = recipe.imageUrl)

            Row(Modifier.padding(top = 7.dp)) {
                Image(
                    painter = painter,
                    modifier = Modifier.size(100.dp).padding(top = 10.dp, bottom = 10.dp),
                    contentDescription = RECIPE_IMAGE
                )

                Column {
                    Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                        Text(
                            recipe.title, modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier.background(recipe.rating.getRatingBoxColor())
                                .width(40.dp), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                recipe.rating, color = Color.White, modifier = Modifier.padding(
                                    start = 5.dp, end = 5.dp
                                )
                            )
                        }
                    }

                    Text(
                        recipe.courseType, modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
                    )

                    Text(recipe.duration, modifier = Modifier.padding(start = 10.dp, bottom = 5.dp))
                }
            }
        }
    }
}

@Composable
fun SeeAllCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.height(SEE_ALL_CARD_HEIGHT).fillMaxWidth().padding(
            start = HOME_PADDING_START,
            bottom = SEE_ALL_CARD_PADDING_BOTTOM,
            end = SEE_ALL_CARD_PADDING_END
        ).clickable { onClick() }, elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            Text(SEE_ALL)
        }
    }
}

@Composable
fun CourseOptionCardRow() {
    LazyRow {
        item {
            OptionCard(text = BREAKFAST, onClick = {

            })
            OptionCard(text = LUNCH, onClick = {

            })
            OptionCard(text = DINNER, onClick = {

            })
            OptionCard(text = SNACKS, onClick = {

            })
        }
    }
}