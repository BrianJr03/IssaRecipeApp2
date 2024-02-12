package composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ArrowIcon() {
    Icon(
        imageVector = Icons.Rounded.ArrowForward,
        contentDescription = "",
        modifier = Modifier.padding(end = 10.dp).size(20.dp)
    )
}