package domain.usecase

import domain.model.SlackMessage
import infra.command.PostSlackMessageCommandServiceImpl

class PostMessageUseCase {
    fun postMessages(messages: List<SlackMessage>) {
        PostSlackMessageCommandServiceImpl().postMessage(messages = messages)
    }
}