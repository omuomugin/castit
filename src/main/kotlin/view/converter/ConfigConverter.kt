package view.converter

import presentation.model.Config as PresentationConfig
import presentation.model.Message as PresentationMessage
import view.model.Config as ViewConfig
import view.model.Message as ViewMessage

object ConfigConverter {
    fun convert(viewConfig: ViewConfig): PresentationConfig {
        return PresentationConfig(
            token = viewConfig.token,
            casts = viewConfig.casts.map {
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