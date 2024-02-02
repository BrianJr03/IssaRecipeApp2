package components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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