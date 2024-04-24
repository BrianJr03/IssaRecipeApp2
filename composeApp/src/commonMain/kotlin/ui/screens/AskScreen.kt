package ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import blocs.askScreen.AskScreenComponent
import blocs.askScreen.AskScreenEvent
import constants.AI_LABEL
import constants.DEFAULT_API_KEY_VALUE
import constants.ERROR_LABEL
import constants.USER_LABEL
import kotlinx.coroutines.launch
import models.local.Chat
import models.local.SqlDataSourceImpl
import models.local.Status
import repositories.API
import ui.composables.AskScreenTopAppBar
import ui.composables.ChatSection
import ui.composables.CustomBottomBar
import ui.composables.EmptyPromptDialog

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
        } catch (_: NullPointerException) {
        }
    }

    // -----------------

    val focusManager = LocalFocusManager.current

    val promptText = remember { mutableStateOf("") }

    val isEmptyPromptDialogShowing = remember { mutableStateOf(false) }
    val isGeminiTyping = remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    val chats = remember { listOf<Chat>().toMutableStateList() }

    val scrollState = rememberScrollState()
    val chatListState = rememberLazyListState()

    LaunchedEffect(key1 = Unit, block = {
        chatListState.animateScrollToItem(chats.size)
    })


//    val sendOnClick = {
//        focusManager.clearFocus()
//        if (chats.isEmpty()) {
//            scope.launch {
//                chatListState.animateScrollToItem(0)
//            }
//        }
//
//        if (promptText.value.isEmpty() || promptText.value.isBlank()) {
//            isEmptyPromptDialogShowing.value = true
//        } else {
//            val prompt = promptText.value
//            promptText.value = ""
//            scope.launch {
//
////                viewModel.getAskResponse(
////                    userPrompt = prompt,
////                    context = savedAskContext,
////                    model = savedModel,
////                    dao = dao
////                )
//
//            }
//
//        }
//    }

    EmptyPromptDialog(
        isShowing = isEmptyPromptDialogShowing.value,
        onDismissRequest = {

        }
    )

    Scaffold(
        topBar = {
            AskScreenTopAppBar(
                isGeminiTyping = isGeminiTyping.value,
                onBackClick = {
                    onEvent(
                        AskScreenEvent.OnNavBack
                    )
                }
            )
        },
        bottomBar = {
            CustomBottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 30.dp, top = 5.dp),
                status = Status.Idle,
                onSendClick = { text, images ->
                    val myChat = Chat(
                        fullTimeStamp = "",
                        text = text,
                        dateSent = "",
                        timeSent = "",
                        senderLabel = USER_LABEL
                    )
                    chats.add(myChat)
                    scope.launch {
                        isGeminiTyping.value = true

                        val status = API.geminiRepository.generate(
                            apiKey = apiKey.value,
                            prompt = text,
                            images = images
                        )

                        when (status) {
                            is Status.Success -> {
                                isGeminiTyping.value = false
                                data.value = status.data
                                val aiChat = Chat(
                                    fullTimeStamp = "",
                                    text = data.value,
                                    dateSent = "",
                                    timeSent = "",
                                    senderLabel = AI_LABEL
                                )
                                chats.add(aiChat)
                                chatListState.animateScrollToItem(chats.size)
                            }

                            is Status.Error -> {
                                isGeminiTyping.value = false
                                data.value = status.message
                                val errorCHat = Chat(
                                    fullTimeStamp = "",
                                    text = data.value,
                                    dateSent = "",
                                    timeSent = "",
                                    senderLabel = ERROR_LABEL
                                )
                                chats.add(errorCHat)
                                chatListState.animateScrollToItem(chats.size)
                            }

                            else -> Unit
                        }
                    }
                },
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            ChatSection(
                chats = chats,
                listState = chatListState,
                modifier = Modifier
                    .weight(.90f)
                    .clickable(
                        interactionSource = interactionSource, indication = null
                    ) {
                        focusManager.clearFocus()
                    }
            ) { chat ->
                chats.remove(chat)
            }
        }
    }
}