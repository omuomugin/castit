package domain.usecase

import domain.model.SlackMessage
import infra.command.PostSlackMessageCommandService

class PostMessageUseCase(
    private val service: PostSlackMessageCommandService
) {
    fun postMessages(messages: List<SlackMessage>) {
        service.postMessage(messages = messages)
    }
}