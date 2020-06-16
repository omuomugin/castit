package infra.command

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import domain.model.SlackMessage
import infra.converter.SlackMessageConverter
import infra.model.exception.SlackPostFailedException
import infra.model.response.SlackPostMessageResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PostSlackMessageCommandServiceImpl : PostSlackMessageCommandService {
    override fun postMessageAsync(message: SlackMessage): Deferred<SlackPostMessageResponse> {
        val slackMessage = SlackMessageConverter.convert(message)

        return GlobalScope.async {
            val (_, response, result) = Fuel.post(
                "https://slack.com/api/chat.postMessage", listOf(
                    "token" to slackMessage.token,
                    "channel" to slackMessage.channel,
                    "text" to slackMessage.text
                )
            ).awaitStringResponseResult()

            result.fold(
                {
                    SlackPostMessageResponse(
                        message = "post to ${message.channel}. " +
                                "message={${message.text}}, " +
                                "response=${String(response.data)}"
                    )
                },
                {
                    throw SlackPostFailedException("Failed to post to ${message.channel}. message={${message.text}}")
                }
            )
        }
    }
}