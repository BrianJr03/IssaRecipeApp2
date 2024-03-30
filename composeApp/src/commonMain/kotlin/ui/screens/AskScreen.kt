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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import blocs.askScreen.AskScreenComponent
import blocs.askScreen.AskScreenEvent
import kotlinx.coroutines.launch
import models.local.SqlDataSourceImpl
import models.local.Status
import repositories.API
import ui.composables.CustomBottomBar
import util.DEFAULT_API_KEY_VALUE

@Composable
fun AskScreenComponent.AskScreen(
    sqlDataSourceImpl: SqlDataSourceImpl
) {
    val scope = rememberCoroutineScope()
    val data = rememberSaveable { mutableStateOf("") }

    val apiKey = rememberSaveable { mutableStateOf(DEFAULT_API_KEY_VALUE) }

    LaunchedEffect(2) {
        try {
            sqlDataSourceImpl.settings.collect {
                apiKey.value = it.apiKey
            }
        } catch (_: NullPointerException) {}
    }

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
                        when (val status = API.geminiRepository.generate(
                            apiKey = apiKey.value,
                            prompt = text,
                            images = images
                        )) {
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