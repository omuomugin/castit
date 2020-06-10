package view.model

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val token: String,
    val casts: List<Message>
)

@Serializable
data class Message(
    val channel: String,
    val content: String
)
