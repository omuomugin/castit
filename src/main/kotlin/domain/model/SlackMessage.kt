package domain.model

data class SlackMessage(
    val token: String,
    val channel: String,
    val text: String
)