package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.local.Recipe
import blocs.recipeScreen.RecipeScreenComponent
import blocs.recipeScreen.RecipeScreenEvent
import com.mikepenz.markdown.compose.Markdown
import com.mikepenz.markdown.model.markdownColor
import com.seiko.imageloader.rememberImagePainter
import constants.RECIPE_IMAGE
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.local.SqlDataSourceImpl
import ui.composables.FavoriteDialog
import ui.composables.RecipeScreenTopAppBar
import util.extractRecipeTitle
import util.getRatingBoxColor
import util.negate

@Composable
fun RecipeScreenComponent.RecipeScreen(
    recipe: Recipe,
    sqlDataSourceImpl: SqlDataSourceImpl
) {
    val scope = rememberCoroutineScope()
    val favoriteRecipeTitle = remember { mutableStateOf(recipe.content.extractRecipeTitle()) }
    val isFavoriteDialogShowing = remember { mutableStateOf(false) }
    val isFavoriteIconHidden = remember { mutableStateOf(false) }

    FavoriteDialog(
        isShowing = isFavoriteDialogShowing.value,
        textFieldValue = favoriteRecipeTitle.value,
        onValueChange = {
            favoriteRecipeTitle.value = it
        },
        onConfirmClick = {
            scope.launch {
                sqlDataSourceImpl.insertFavoriteRecipe(
                    imageUrl = recipe.imageUrl,
                    title = favoriteRecipeTitle.value,
                    content = recipe.content,
                    courseType = recipe.courseType,
                    duration = recipe.duration,
                    rating = recipe.rating,
                )
                sqlDataSourceImpl.updateRecentRecipe(
                    recipe = recipe,
                    title = favoriteRecipeTitle.value
                )
                delay(500)
                isFavoriteIconHidden.value = true
            }
            isFavoriteDialogShowing.value = false
        },
        onDismissRequest = {
            isFavoriteDialogShowing.value = false
        }
    )

    Scaffold(
        topBar = {
            RecipeScreenTopAppBar(
                isFavoriteHidden = isFavoriteIconHidden.value,
                onFavoriteClick = {
                    isFavoriteDialogShowing.negate()
                },
                onBackClick = {
                    onEvent(
                        RecipeScreenEvent.OnNavBack
                    )
                }
            )
        }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            item {
                if (recipe.imageUrl.isNotBlank()) {
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
                }
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

                Markdown(
                    content = recipe.content,
                    colors = markdownColor(
                        text = LocalContentColor.current,
                        codeText = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    modifier = Modifier.wrapContentWidth().padding(
                        start = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
                )
            }
        }
    }
}