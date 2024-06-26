package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blocs.favoritesScreen.FavsScreenComponent
import blocs.favoritesScreen.FavsScreenEvent
import util.COLOR_STEEL_BLUE
import util.cards.HORIZONTAL_CARD_HEIGHT
import util.getCardInListColor
import models.local.Recipe
import models.local.SqlDataSourceImpl
import models.local.toRecipe
import ui.composables.DefaultTextField
import ui.composables.DefaultTopAppBar
import ui.composables.HorizontalRecipeCard

@Composable
fun FavsScreenComponent.FavoritesScreen(
    sqlDataSourceImpl: SqlDataSourceImpl
) {
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val favoriteRecipes = rememberSaveable { mutableStateOf(listOf<Recipe>()) }

    val filteredRecipes = remember(searchQuery, favoriteRecipes) {
        derivedStateOf {
            favoriteRecipes.value.filter { recipe ->
                recipe.title.contains(searchQuery.value, ignoreCase = true)
            }
        }
    }

    LaunchedEffect(1) {
        sqlDataSourceImpl.recipes.collect {
            favoriteRecipes.value = it.map { sqlRecipe -> sqlRecipe.toRecipe() }
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBar {
                onEvent(
                    FavsScreenEvent.OnNavBack
                )
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            if (filteredRecipes.value.isEmpty()) {
                Text(
                    "No Favorites",
                    color = COLOR_STEEL_BLUE,
                    fontSize = 20.sp
                )
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(
                        span = StaggeredGridItemSpan.FullLine
                    ) {
                        DefaultTextField(
                            placeholderStr = "Search Favorites",
                            value = searchQuery.value,
                            onValueChange = {
                                searchQuery.value = it
                            }
                        )
                    }

                    items(filteredRecipes.value.size) { index ->
                        HorizontalRecipeCard(
                            recipe = favoriteRecipes.value.reversed()[index],
                            color = getCardInListColor(index),
                            modifier = Modifier.height(HORIZONTAL_CARD_HEIGHT).padding(10.dp),
                        ) {
                            onEvent(
                                FavsScreenEvent.OnRecipeClick(favoriteRecipes.value.reversed()[index])
                            )
                        }
                    }
                }
            }
        }
    }
}