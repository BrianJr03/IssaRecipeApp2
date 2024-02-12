package ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
private fun ShowDialog(
    title: String,
    isShowing: Boolean,
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
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ShowDialog(
        modifier = modifier,
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
fun EmptyPromptDialog(
    isShowing: Boolean,
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ShowDialog(
        title = "Please provide a prompt",
        modifier = modifier,
        content = {
            Column {
                Text(
                    "The text field can not be empty.",
                    fontSize = 16.sp,
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmClick()
                }) {
                Text(text = "Dismiss", color = Color.White)
            }
        },
        dismissButton = {},
        onDismissRequest = onDismissRequest,
        isShowing = isShowing
    )
}

@Composable
fun DeleteDialog(
    title: String,
    isShowing: Boolean,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ShowDialog(
        title = title,
        modifier = modifier,
        content = {
            Column {
                Text(
                    "This can't be undone.",
                    fontSize = 16.sp,
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onDeleteClick()
                }) {
                Text(text = "Delete", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismissRequest()
                }) {
                Text(text = "Cancel", color = Color.White)
            }
        },
        onDismissRequest = onDismissRequest,
        isShowing = isShowing
    )
}