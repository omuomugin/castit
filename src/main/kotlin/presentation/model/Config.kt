package presentation.model

data class Config(
    val token: String,
    val casts: List<Message>
)

data class Message(
    val channel: String,
    val content: String
)