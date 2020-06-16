package infra.command

import domain.model.SlackMessage
import infra.model.response.SlackPostMessageResponse
import kotlinx.coroutines.Deferred

interface PostSlackMessageCommandService {
    fun postMessageAsync(message: SlackMessage): Deferred<SlackPostMessageResponse>
}
