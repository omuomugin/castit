package infra.converter

import domain.model.SlackMessage as DomainSlackMessage
import infra.model.SlackMessage as InfraSlackMessage

object SlackMessageConverter {
    fun convert(slackMessage: DomainSlackMessage): InfraSlackMessage {
        return InfraSlackMessage(
            token = slackMessage.token,
            channel = slackMessage.channel,
            text = slackMessage.text
        )
    }
}
