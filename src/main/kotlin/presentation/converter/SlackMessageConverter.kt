package presentation.converter

import domain.model.SlackMessage
import presentation.model.Config

object SlackMessageConverter {
    fun convert(config: Config): List<SlackMessage> {
        return config.casts.map {
            SlackMessage(
                token = config.token,
                channel = it.channel,
                text = it.content
            )
        }
    }
}
