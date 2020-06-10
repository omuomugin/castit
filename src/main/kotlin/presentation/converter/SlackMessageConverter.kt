package presentation.converter

import domain.model.SlackMessage
import presentation.model.Config

object SlackMessageConverter {
    fun convert(config: Config): SlackMessage {
        return SlackMessage()
    }
}