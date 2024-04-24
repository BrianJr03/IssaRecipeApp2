package ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import constants.AI_LABEL
import constants.ERROR_LABEL
import models.local.Chat
import util.COLOR_PALE_VIOLET_RED
import util.COLOR_STEEL_BLUE
import util.COLOR_WHEAT
import util.negate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatSection(
    chats: List<Chat>,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    onDeleteChat: (chat: Chat) -> Unit
) {
    val isChatGptTyping = remember { mutableStateOf(false) }
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
    } else {
        LazyColumn(modifier = modifier, state = listState) {
            items(chats.size) { index ->
                val chat = chats[index]
                val isHumanChatBox = chat.senderLabel != AI_LABEL && chat.senderLabel != ERROR_LABEL
                val isDeleteDialogShowing = remember { mutableStateOf(false) }

                val isShowingLoadingBar = remember {
                    derivedStateOf {
                        (isChatGptTyping.value && index == chats.size - 1)
                    }
                }

                DeleteDialog(
                    title = "Delete this Chat?",
                    isShowing = isDeleteDialogShowing.value,
                    onDeleteClick = {
                        onDeleteChat(chat)
                        isDeleteDialogShowing.negate()
                    },
                    onDismissRequest = {
                        isDeleteDialogShowing.negate()
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
                        color = COLOR_STEEL_BLUE,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isHumanChatBox) COLOR_STEEL_BLUE else COLOR_PALE_VIOLET_RED)
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