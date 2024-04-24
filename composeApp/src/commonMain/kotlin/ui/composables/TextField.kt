package ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import util.COLOR_STEEL_BLUE

@Composable
fun DefaultTextField(
    value: String,
    placeholderStr: String,
    modifier: Modifier = Modifier,
    maxCount: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    isError: Boolean = false,
    onValueChange: ((str: String) -> Unit)?,
    onDone: (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null
) {
    val showErrorColor = remember {
        mutableStateOf(false)
    }
    TextField(
        modifier = modifier.padding(
            start = 10.dp,
            end = 10.dp,
            bottom = 10.dp
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
        placeholder = {
            Text(
                text = placeholderStr,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = COLOR_STEEL_BLUE,
            unfocusedContainerColor = COLOR_STEEL_BLUE,
            focusedIndicatorColor = if (showErrorColor.value) Color.Red else Color.Transparent,
            unfocusedIndicatorColor = if (showErrorColor.value) Color.Red else Color.Transparent,
            disabledContainerColor = Color.LightGray,
        ),
        shape = RoundedCornerShape(24),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onDone?.invoke()
        }),
    )
}