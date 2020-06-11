package infra.model

data class SlackMessage(
    val token: String,
    val channel: String,
    val text: String
)
