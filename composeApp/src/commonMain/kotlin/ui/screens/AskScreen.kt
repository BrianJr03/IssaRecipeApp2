package ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blocs.askScreen.AskScreenComponent
import blocs.askScreen.AskScreenEvent
import constants.GREEN
import kotlinx.coroutines.launch
import ui.composables.ArrowIcon
import ui.composables.DeleteDialog
import ui.composables.EmptyPromptDialog

@Composable
fun AskScreenComponent.AskScreen() {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val promptText = remember { mutableStateOf("") }

    val isEmptyPromptDialogShowing = remember { mutableStateOf(false) }
    val isChatGptTyping = remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    val chats = mutableListOf<Chat>()

    val scrollState = rememberScrollState()
    val chatListState = rememberLazyListState()

    LaunchedEffect(key1 = 1, block = {
        chatListState.animateScrollToItem(chats.size)
    })

    val sendOnClick = {
        focusManager.clearFocus()
        if (chats.isEmpty()) {
            scope.launch {
                chatListState.animateScrollToItem(0)
            }
        }
        if ("savedApiKey".isEmpty()) { // TODO - FIX
            onEvent(
                AskScreenEvent.OnNavToSettings
            )
//            context.showToast(API_KEY_REQUIRED)
        } else if (promptText.value.isEmpty() || promptText.value.isBlank()) {
            isEmptyPromptDialogShowing.value = true
        } else {
            val prompt = promptText.value
            promptText.value = ""
            scope.launch {
                val myChat = Chat(
                    fullTimeStamp = "LocalDateTime.now().toString()",
                    text = prompt,
                    dateSent = "Test",
                    timeSent = "1:10",
                    senderLabel = "USER_LABEL"
                )
                chats.add(myChat)
                chatListState.animateScrollToItem(chats.size)
//                viewModel.getAskResponse(
//                    userPrompt = prompt,
//                    context = savedAskContext,
//                    model = savedModel,
//                    dao = dao
//                )
                val chatGptChat = Chat(
                    fullTimeStamp = "LocalDateTime.now().toString()",
                    text = "prompt",
                    dateSent = "Test",
                    timeSent = "1:10",
                    senderLabel = "AI_LABEL"
                )
                chats.add(chatGptChat)
                chatListState.animateScrollToItem(chats.size)
            }

        }
    }

    EmptyPromptDialog(
        isShowing = isEmptyPromptDialogShowing.value,
        onConfirmClick = {
            isEmptyPromptDialogShowing.value = false
        },
        onDismissRequest = {
            isEmptyPromptDialogShowing.value = false
        }
    )

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .scrollable(scrollState, orientation = Orientation.Vertical)
                .padding(it)
                .navigationBarsPadding()
        )
        {
            Spacer(Modifier.height(5.dp))

            ChatHeader(
                isChatGptTyping = isChatGptTyping.value,
                modifier = Modifier.padding(5.dp),
                onResetAllChats = {
                    chats.clear()
                },
                onNavToAskContext = {
                    onEvent(
                        AskScreenEvent.OnNavToAskContext
                    )
                }
            )

            ChatSection(
                chats = chats,
                isChatGptTyping = isChatGptTyping.value,
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

            ChatTextFieldRow(
                promptText = promptText.value,
                textFieldOnValueChange = { text -> promptText.value = text },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
                    .onFocusEvent { event ->
                        if (event.isFocused) {
                            scope.launch {
                                scrollState.animateScrollTo(scrollState.maxValue)
                            }
                        }
                    },
                sendIconModifier = Modifier
                    .size(30.dp)
                    .clickable { sendOnClick() },
            )
        }
    }
}


@Composable
fun ChatHeader(
    isChatGptTyping: Boolean,
    modifier: Modifier = Modifier,
    headerTextModifier: Modifier = Modifier,
    onResetAllChats: () -> Unit,
    onNavToAskContext: () -> Unit,
) {
    val isDeleteDialogShowing = remember { mutableStateOf(false) }

    DeleteDialog(
        title = "Reset this Conversation?",
        isShowing = isDeleteDialogShowing.value,
        onDeleteClick = {
            isDeleteDialogShowing.value = false
        },
        onDismissRequest = {
            isDeleteDialogShowing.value = false
            onResetAllChats()
        }
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (isChatGptTyping) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(.1f))
                Text(
                    "ChefGPT is typing",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
//                LottieLoading(
//                    isShowing = isChatGptTyping,
//                    modifier = Modifier.size(40.dp)
//                )
                Spacer(modifier = Modifier.weight(.1f))
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
            ) {
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    "Ask",
                    color = GREEN,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = headerTextModifier
                )
                Spacer(modifier = Modifier.weight(.1f))
                ArrowIcon()
                Spacer(modifier = Modifier.width(15.dp))
                ArrowIcon()
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatSection(
    chats: List<Chat>,
    isChatGptTyping: Boolean,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    onDeleteChat: (chat: Chat) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    if (chats.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(50.dp)
        ) {
            Text(
                "No Chats Recorded",
                style = TextStyle(fontSize = 20.sp)
            )
        }
    }

    LazyColumn(modifier = modifier, state = listState) {
        items(chats.size) { index ->
            val chat = chats[index]
            val isHumanChatBox = chat.senderLabel != "USER_LABEL"
            val isDeleteDialogShowing = remember { mutableStateOf(false) }

            val isShowingLoadingBar = remember {
                derivedStateOf {
                    (isChatGptTyping && index == chats.size - 1)
                }
            }

            DeleteDialog(
                title = "Delete this Chat?",
                isShowing = isDeleteDialogShowing.value,
                onDeleteClick = {
                    isDeleteDialogShowing.value = false
                    onDeleteChat(chat)
                },
                onDismissRequest = {
                   isDeleteDialogShowing.value = false
                }
            )

            ChatBox(
                text = chat.text,
                dateSent = chat.dateSent,
                timeSent = chat.timeSent,
                senderLabel = chat.senderLabel,
                isHumanChatBox = isHumanChatBox,
                isChefGptTyping = isShowingLoadingBar.value,
                modifier = Modifier
                    .padding(10.dp)
                    .indication(interactionSource, LocalIndication.current)
                    .animateItemPlacement(),
                onDeleteChat = {
                    isDeleteDialogShowing.value = true
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChatBox(
    text: String,
    dateSent: String,
    timeSent: String,
    senderLabel: String,
    isHumanChatBox: Boolean,
    isChefGptTyping: Boolean,
    modifier: Modifier = Modifier,
    onDeleteChat: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val isChatInfoShowing = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(.8f),
            horizontalAlignment = if (isHumanChatBox) Alignment.End else Alignment.Start
        ) {
            AnimatedVisibility(visible = isChatInfoShowing.value) {
                Column(
                    horizontalAlignment = if (isHumanChatBox) Alignment.End else Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.padding(
                            start = if (isHumanChatBox) 0.dp else 10.dp,
                            end = if (isHumanChatBox) 10.dp else 0.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            senderLabel,
                            modifier = Modifier
                        )
                        Spacer(Modifier.width(5.dp))
                        Text("•")
                        Spacer(Modifier.width(5.dp))
                        Text(dateSent)
                        Spacer(Modifier.width(5.dp))
                        Text("•")
                        Spacer(Modifier.width(5.dp))
                        Text(timeSent)
                    }
                    Row(
                        modifier = Modifier.padding(
                            start = if (isHumanChatBox) 0.dp else 10.dp,
                            end = if (isHumanChatBox) 10.dp else 0.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Delete",
                            modifier = Modifier.clickable {
                                onDeleteChat()
                                isChatInfoShowing.value = false
                            }
                        )
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isChefGptTyping) {
                    CircularProgressIndicator(
                        color = GREEN,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isHumanChatBox) GREEN else Color.Gray)
                        .combinedClickable(
                            onClick = {
                                focusManager.clearFocus()
                                isChatInfoShowing.value = !isChatInfoShowing.value
                            }
                        )
                ) {
                    CompositionLocalProvider {
                        SelectionContainer {
                            Text(
                                text,
                                color = Color.White,
                                modifier = Modifier.padding(15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatTextFieldRow(
    promptText: String,
    textFieldOnValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    sendIconModifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = promptText,
        onValueChange = textFieldOnValueChange,
        label = {
            Text(
                text = "Enter a prompt",
                color = GREEN,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = GREEN,
            unfocusedTextColor = GREEN,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = GREEN
        ),
        trailingIcon = {
            ArrowIcon(
                modifier = sendIconModifier
            )
        }
    )

    Spacer(Modifier.height(15.dp))
}

data class Chat(
    val fullTimeStamp: String,
    val text: String,
    val dateSent: String,
    val timeSent: String,
    val senderLabel: String,
)