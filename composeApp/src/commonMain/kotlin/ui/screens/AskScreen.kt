package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import blocs.askScreen.AskScreenComponent
import blocs.askScreen.AskScreenEvent
import kotlinx.coroutines.launch
import models.local.Recipe
import models.local.Status
import repositories.API
import ui.composables.CustomBottomBar

@Composable
fun AskScreenComponent.AskScreen() {
    val scope = rememberCoroutineScope()
    val data = rememberSaveable { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            CustomBottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 30.dp, top = 5.dp),
                status = Status.Idle,
                onSendClick = { text, images ->
                    scope.launch {
                        when (val status = API.geminiRepository.generate(text, images)) {
                            is Status.Success -> {
                                data.value = status.data
                            }
                            is Status.Error -> {
                                data.value = status.message
                            }
                            else -> Unit
                        }
                    }
                },
            )
        }
    ) {

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = data.value)
        Button(onClick = {
            onEvent(AskScreenEvent.OnNavBack)
        }) {
            Text("Back - Ask")
        }
    }
}