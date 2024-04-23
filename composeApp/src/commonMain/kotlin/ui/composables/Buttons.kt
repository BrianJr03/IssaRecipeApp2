package ui.composables

import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = {
            onClick()
        },
        content = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = Icons.Rounded.ArrowBack.name
            )
        }
    )
}