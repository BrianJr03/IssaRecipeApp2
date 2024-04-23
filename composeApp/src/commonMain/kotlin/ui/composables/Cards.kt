package ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import constants.BREAKFAST
import constants.DEFAULT_TEXT_STYLE
import constants.DINNER
import constants.LUNCH
import constants.RECIPE_IMAGE
import constants.SEE_ALL
import constants.SNACKS
import constants.cards.*
import constants.home.DEFAULT_PADDING_START
import models.local.Recipe

@Composable
fun VerticalRecipeCard(
    color: Color = Color.White,
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(start = 15.dp, end = 15.dp)
            .clickable { onClick() },
        elevation = CARD_ELEVATION
    ) {
        val painter = rememberImagePainter(url = recipe.imageUrl)

        Box(
            modifier = Modifier.background(color),
            contentAlignment = Alignment.Center
        ) {
//            Column {
            if (recipe.imageUrl.isNotBlank()) {
                Image(
                    painter = painter,
                    modifier = Modifier.fillMaxSize()
                        .padding(
                            top = V_RECIPE_CARD_IMAGE_PADDING_TOP,
                            bottom = V_RECIPE_CARD_IMAGE_PADDING_BOTTOM
                        ),
                    contentDescription = RECIPE_IMAGE
                )
            } else {
                Text(
                    text = recipe.title.replace(" ", "\n"),
                    style = DEFAULT_TEXT_STYLE,
                    modifier = Modifier
                )
            }
//                Text(
//                    recipe.courseType,
//            style = DEFAULT_TEXT_STYLE
            //                    modifier = Modifier.padding(
//                        start = V_RECIPE_CARD_CONTENT_PADDING_START,
//                        bottom = V_RECIPE_CARD_CONTENT_PADDING_BOTTOM
//                    )
//                )
//                Row(
//                    modifier = Modifier.padding(
//                        start = V_RECIPE_CARD_CONTENT_PADDING_START,
//                        bottom = V_RECIPE_CARD_CONTENT_PADDING_BOTTOM,
//                        end = V_RECIPE_CARD_CONTENT_PADDING_END
//                    )
//                ) {
//                    Text(recipe.duration, style = DEFAULT_TEXT_STYLE)
//                    Spacer(Modifier.weight(1f))
//                    Box(
//                        modifier = Modifier.background(recipe.rating.getRatingBoxColor())
//                            .width(V_RECIPE_CARD_RATING_BOX_HEIGHT),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            recipe.rating, color = Color.White,
            //                       style = DEFAULT_TEXT_STYLE,
            //                            modifier = Modifier.padding(
//                                start = V_RECIPE_CARD_RATING_BOX_PADDING_START,
//                                end = V_RECIPE_CARD_RATING_BOX_PADDING_END
//                            )
//                        )
//                    }
//                }
//            }
        }
    }
}

@Composable
fun CardButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .height(OPTION_CARD_HEIGHT)
        .padding(
            start = DEFAULT_PADDING_START,
            end = OPTION_CARD_PADDING_END
        ).clickable { onClick() },
    boxModifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = modifier,
        elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = boxModifier,
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    style = DEFAULT_TEXT_STYLE,
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
    modifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = boxModifier, contentAlignment = Alignment.Center
        ) {
//            val painter = rememberImagePainter(url = recipe.imageUrl)
            Text(
                text = recipe.title,
                style = DEFAULT_TEXT_STYLE,
                modifier = Modifier.padding(
                    start = 5.dp, bottom = 10.dp
                )
            )
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        top = 7.dp,
//                        start = 10.dp,
//                        bottom = 5.dp
//                    )
//            ) {
//                Image(
//                    painter = painter,
//                    modifier = Modifier.size(100.dp).padding(top = 5.dp, bottom = 5.dp),
//                    contentDescription = RECIPE_IMAGE
//                )
//
//                Column(modifier = Modifier.padding(start = 10.dp)) {

//                    Row(modifier = Modifier.padding(bottom = 10.dp)) {
//                        Spacer(Modifier.weight(1f))
//                        T
//                        Spacer(Modifier.weight(1f))
//                    }

//                    Text(recipe.title,
//             style = DEFAULT_TEXT_STYLE,
        //                    modifier = Modifier.padding(start = 5.dp, bottom = 10.dp))

//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(
//                            imageVector = Icons.Rounded.Star,
//                            contentDescription = Icons.Rounded.Star.name,
//                            tint = MaterialTheme.colors.primary,
//                            modifier = Modifier.size(20.dp)
//                        )
//
//                        Spacer(Modifier.width(5.dp))
//
//                        Text(recipe.courseType,style = DEFAULT_TEXT_STYLE)
//                    }
//
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(
//                            imageVector = Icons.Rounded.Info,
//                            contentDescription = Icons.Rounded.Info.name,
//                            tint = MaterialTheme.colors.primary,
//                            modifier = Modifier.size(18.dp)
//                        )
//
//                        Spacer(Modifier.width(5.dp))
//
//                        Text(
//                            recipe.duration,
//                            style = DEFAULT_TEXT_STYLE
//                        )
//                    }

//                }
//            }
        }
    }
}

@Composable
fun SeeAllCard(
    modifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CARD_ELEVATION
    ) {
        Box(
            modifier = boxModifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = SEE_ALL,
                style = DEFAULT_TEXT_STYLE
            )
        }
    }
}

@Composable
fun CourseOptionCardRow(
    modifier: Modifier = Modifier,
    optionCard1Modifier: Modifier = Modifier,
    optionCard2Modifier: Modifier = Modifier,
    optionCard3Modifier: Modifier = Modifier,
    optionCard4Modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
    ) {
        item {
            CardButton(
                text = BREAKFAST,
                boxModifier = optionCard1Modifier,
                onClick = {

                })
            CardButton(
                text = LUNCH,
                boxModifier = optionCard2Modifier,
                onClick = {

                })
            CardButton(
                text = DINNER,
                boxModifier = optionCard3Modifier,
                onClick = {

                })
            CardButton(
                text = SNACKS,
                boxModifier = optionCard4Modifier,
                onClick = {

                })
        }
    }
}