package ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun FavoriteDialog(
    isShowing: Boolean,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ShowDialog(
        isShowing = isShowing,
        title = "Favorite",
        content = {
            DefaultTextField(
                placeholderStr = "",
                value = textFieldValue,
                onValueChange = {
                    onValueChange(it)
                }
            )
        },
        confirmButton = {
            Button(onClick = {
                onConfirmClick()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismissRequest()
            }) {
                Text("Cancel")
            }
        },
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
                        Text(
                            option,
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    if (index != options.size - 1) {
                        Divider(color = MaterialTheme.colorScheme.primary)
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
                    onDismissRequest()
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
        onDismissRequest = {},
        isShowing = isShowing
    )
}

@Composable
fun EmptyPromptDialog(
    isShowing: Boolean,
    modifier: Modifier = Modifier,
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
                    onDismissRequest()
                }) {
                Text(text = "Dismiss", color = Color.White)
            }
        },
        dismissButton = {

        },
        onDismissRequest = {
            onDismissRequest()
        },
        isShowing = isShowing
    )
}