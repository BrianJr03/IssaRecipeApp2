package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.local.Recipe
import blocs.recipeScreen.RecipeScreenComponent
import blocs.recipeScreen.RecipeScreenEvent
import com.seiko.imageloader.rememberImagePainter
import constants.RECIPE_IMAGE
import constants.cards.VERTICAL_RECIPE_CARD_IMAGE_HEIGHT
import constants.cards.V_RECIPE_CARD_CONTENT_PADDING_BOTTOM
import constants.cards.V_RECIPE_CARD_CONTENT_PADDING_END
import constants.cards.V_RECIPE_CARD_CONTENT_PADDING_START
import constants.cards.V_RECIPE_CARD_IMAGE_PADDING_BOTTOM
import constants.cards.V_RECIPE_CARD_IMAGE_PADDING_TOP
import constants.cards.V_RECIPE_CARD_RATING_BOX_HEIGHT
import constants.cards.V_RECIPE_CARD_RATING_BOX_PADDING_END
import constants.cards.V_RECIPE_CARD_RATING_BOX_PADDING_START
import util.getRatingBoxColor

@Composable
fun RecipeScreenComponent.RecipeScreen(recipe: Recipe) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                Image(
                    painter = rememberImagePainter(
                        url = recipe.imageUrl
                    ),
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    contentDescription = RECIPE_IMAGE
                )
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .background(recipe.rating.getRatingBoxColor())
                        .width(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        recipe.rating,
                        color = Color.White,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 5.dp
                        )
                    )
                }
                Text(
                    recipe.content,
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
                )
                Row {
                    Spacer(Modifier.weight(1f))
                    Button(
                        modifier = Modifier.padding(bottom = 10.dp),
                        onClick = {
                            onEvent(RecipeScreenEvent.OnNavBack)
                        }) {
                        Text("Back")
                    }
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}