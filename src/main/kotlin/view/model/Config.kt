package view.model

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val token: String,
    val casts: List<Cast>
)

@Serializable
data class Cast(
    val name: String,
    val messages: List<Message>
)

@Serializable
data class Message(
    val channel: String,
    val content: String
)
