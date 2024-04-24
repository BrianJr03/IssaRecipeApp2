package models.local

data class Chat(
    val fullTimeStamp: String,
    val text: String,
    val dateSent: String,
    val timeSent: String,
    val senderLabel: String,
)