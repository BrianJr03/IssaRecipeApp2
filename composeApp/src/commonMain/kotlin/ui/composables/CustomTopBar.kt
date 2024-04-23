package ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import constants.DEFAULT_TEXT_STYLE
import constants.defaultVerticalGradient

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
        BackButton(
            modifier = Modifier
                .clip(RoundedCornerShape(corner = CornerSize(30.dp)))
                .background(defaultVerticalGradient())
        ) {
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
        BackButton(
            modifier = Modifier
                .clip(RoundedCornerShape(corner = CornerSize(30.dp)))
                .background(defaultVerticalGradient())
        ) {
            onBackClick()
        }
        Spacer(Modifier.width(30.dp))
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
                Text("Add to Favorites",
                    style = DEFAULT_TEXT_STYLE,
                    modifier = Modifier.clickable {
                        onFavoriteClick()
                    }
                )
            }
        }
    }
}

@Composable
fun FavoritesScreenTopAppBar(
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
        BackButton(
            modifier = Modifier
                .clip(RoundedCornerShape(corner = CornerSize(30.dp)))
                .background(defaultVerticalGradient())
        ) {
            onBackClick()
        }
    }
}