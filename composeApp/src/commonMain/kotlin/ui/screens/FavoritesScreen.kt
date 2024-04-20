package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.MaterialTheme
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
import models.local.Recipe
import models.local.SqlDataSourceImpl
import models.local.toRecipe
import ui.composables.DefaultTextField
import ui.composables.VerticalRecipeCard

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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(15.dp))
        DefaultTextField(
            label = "Search Favorites",
            value = searchQuery.value,
            onValueChange = {
                searchQuery.value = it
            }
        )

        if (filteredRecipes.value.isEmpty()) {
            androidx.compose.material3.Text(
                "No Favorites",
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp
            )
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
            ) {
                items(filteredRecipes.value.size) { index ->
                    VerticalRecipeCard(
                        favoriteRecipes.value.reversed()[index],
                        modifier = Modifier.height(250.dp).padding(10.dp)
                    ) {
                        onEvent(
                            FavsScreenEvent.OnNavBack
                        )
                    }
                }
            }
        }
    }
}