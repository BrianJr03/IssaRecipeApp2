package ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import constants.GREEN

@Composable
fun DefaultTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    maxCount: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    isError: Boolean = false,
    onValueChange: ((str: String) -> Unit)?,
    onDone: (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val showErrorColor = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier.padding(
            start = 15.dp,
            end = 15.dp,
            bottom = 15.dp
        ),
        value = value,
        isError = isError,
        readOnly = readOnly,
        onValueChange = { str ->
            if (str.length <= maxCount) {
                if (str.isNotBlank()) {
                    showErrorColor.value = false
                } else if (str.toIntOrNull() != null) {
                    showErrorColor.value = false
                }
            }
            onValueChange?.invoke(str)
        },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = if (showErrorColor.value) Color.Red else GREEN,
            unfocusedIndicatorColor = if (showErrorColor.value) Color.Red
            else MaterialTheme.colorScheme.background
        ),
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onDone?.invoke()
        }),
    )
}