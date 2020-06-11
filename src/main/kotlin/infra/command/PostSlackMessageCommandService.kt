package infra.command

import domain.model.SlackMessage

interface PostSlackMessageCommandService {
    fun postMessage(messages: List<SlackMessage>)
}