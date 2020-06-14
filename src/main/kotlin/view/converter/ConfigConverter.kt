package view.converter

import view.model.Cast
import presentation.model.Config as PresentationConfig
import presentation.model.Message as PresentationMessage
import view.model.Message as ViewMessage

object ConfigConverter {
    fun convert(cast: Cast, token: String): PresentationConfig {
        return PresentationConfig(
            token = token,
            casts = cast.messages.map {
                convert(it)
            }
        )
    }

    private fun convert(viewMessage: ViewMessage): PresentationMessage {
        return PresentationMessage(
            channel = viewMessage.channel,
            content = viewMessage.content
        )
    }
}