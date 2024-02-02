package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navigation.askScreen.GenerateScreenComponent
import navigation.generateScreen.GenerateScreenEvent

@Composable
fun GenerateScreen(component: GenerateScreenComponent) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            component.onEvent(GenerateScreenEvent.OnNavBack)
        }) {
            Text("Back - Generate")
        }
    }
}