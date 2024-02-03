package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.local.RecentRecipe
import blocs.recipeScreen.RecipeScreenComponent
import blocs.recipeScreen.RecipeScreenEvent

@Composable
fun RecipeScreenComponent.RecipeScreen(recentRecipe: RecentRecipe) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(recentRecipe.toString())
        Spacer(Modifier.height(10.dp))
        Button(onClick = {
            onEvent(RecipeScreenEvent.OnNavBack)
        }) {
            Text("Back - Recipe")
        }
    }
}