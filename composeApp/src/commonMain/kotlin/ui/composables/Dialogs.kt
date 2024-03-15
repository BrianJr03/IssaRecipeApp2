package ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
private fun ShowDialog(
    isShowing: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)?,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
) {
    if (isShowing) {
        AlertDialog(
            title = { Text(title, fontSize = 22.sp) },
            text = content,
            confirmButton = confirmButton,
            dismissButton = dismissButton,
            onDismissRequest = onDismissRequest,
            modifier = modifier,
        )
    }
}

@Composable
fun ShareDialog(
    isShowing: Boolean,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ShowDialog(
        isShowing = isShowing,
        title = "Share",
        content = {
            Text("Test")
        },
        confirmButton = {
            Button(onClick = {
                onConfirmClick()
            }) {
                Text("Close")
            }
        },
        dismissButton = {},
        onDismissRequest = onDismissRequest
    )
}

@Composable
fun OptionsDialog(
    isShowing: Boolean,
    title: String,
    options: List<String>,
    onSelectItem: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    ShowDialog(
        title = title,
        content = {
            LazyColumn {
                items(options.size) { index ->
                    val option = options[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectItem(option)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material3.Text(
                            option,
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    if (index != options.size - 1) {
                        Divider(color = MaterialTheme.colors.primary)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {},
        onDismissRequest = onDismissRequest,
        isShowing = isShowing
    )
}