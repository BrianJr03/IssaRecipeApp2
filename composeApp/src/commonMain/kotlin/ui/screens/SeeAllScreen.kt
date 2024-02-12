package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import blocs.seeAllScreen.SeeAllScreenComponent
import ui.composables.HorizontalRecipeCard
import constants.home.HEADER_ROW_PADDING_TOP
import models.local.TEST_RECENT_RECIPES

@Composable
fun SeeAllScreenComponent.SeeAllScreen() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(
            top = HEADER_ROW_PADDING_TOP,
        ),
    ) {
        items(TEST_RECENT_RECIPES.size) { index ->
            HorizontalRecipeCard(TEST_RECENT_RECIPES[index]) {
//                onEvent(
//                    HomeScreenEvent.OnRecentRecipeClick(TEST_RECENT_RECIPES[index])
//                )
            }
        }
    }
}