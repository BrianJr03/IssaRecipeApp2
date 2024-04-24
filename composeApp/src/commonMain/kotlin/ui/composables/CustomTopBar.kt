package ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.animation.DefaultLoadingAnimation
import util.COLOR_STEEL_BLUE
import util.DEFAULT_TEXT_STYLE
import util.TOP_BAR_TEXT_STYLE

@Composable
fun DefaultTopAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        contentPadding = PaddingValues(
            start = 15.dp,
            top = 15.dp,
            bottom = 15.dp
        )
    ) {
        BackButton {
            onBackClick()
        }
    }
}

@Composable
fun RecipeScreenTopAppBar(
    isFavoriteHidden: Boolean,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        contentPadding = PaddingValues(
            start = 15.dp,
            top = 15.dp,
            bottom = 15.dp
        )
    ) {
        BackButton {
            onBackClick()
        }
        Spacer(Modifier.width(20.dp))
        AnimatedVisibility(!isFavoriteHidden) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    onFavoriteClick()
                }) {
                IconButton(
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    onClick = { onFavoriteClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = Icons.Default.Favorite.name,
                        modifier = Modifier.size(75.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
                Text(
                    text = "Add to Favorites",
                    style = TOP_BAR_TEXT_STYLE,
                    modifier = Modifier.clickable {
                        onFavoriteClick()
                    }
                )
            }
        }
    }
}

@Composable
fun AskScreenTopAppBar(
    isGeminiTyping: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        contentPadding = PaddingValues(
            start = 15.dp,
            top = 15.dp,
            bottom = 15.dp
        )
    ) {
        BackButton {
            onBackClick()
        }

        Spacer(Modifier.width(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isGeminiTyping) "Gemini is typing" else "Ask Gemini",
                style = TOP_BAR_TEXT_STYLE
            )

            Spacer(Modifier.width(15.dp))

            AnimatedVisibility(isGeminiTyping) {
                DefaultLoadingAnimation()
            }
        }
    }
}