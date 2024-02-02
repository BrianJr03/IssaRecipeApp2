package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.homeScreen.HomeScreenComponent
import navigation.homeScreen.HomeScreenEvent

@Composable
fun HomeScreen(component: HomeScreenComponent) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyRow {
            item {
                OptionCard(
                    text = "Ask",
                    onClick = {
                        component.onEvent(
                            HomeScreenEvent.OnAskClick
                        )
                    }
                )
                OptionCard(
                    text = "Generate",
                    onClick = {
                        component.onEvent(
                            HomeScreenEvent.OnGenerateClick
                        )
                    }
                )
                OptionCard(
                    text = "Favorites",
                    onClick = {
                        component.onEvent(
                            HomeScreenEvent.OnFavoritesClick
                        )
                    }
                )
                OptionCard(
                    text = "Settings",
                    onClick = {
                        component.onEvent(
                            HomeScreenEvent.OnSettingsClick
                        )
                    }
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
            .height(60.dp)
            .padding(10.dp)
            .clickable { onClick() },
        elevation = 10.dp
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
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                )
            }
        }
    }
}