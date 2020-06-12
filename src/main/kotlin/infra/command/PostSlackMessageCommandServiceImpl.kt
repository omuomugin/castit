package infra.command

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import domain.model.SlackMessage
import infra.converter.SlackMessageConverter
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class PostSlackMessageCommandServiceImpl : PostSlackMessageCommandService {
    override fun postMessage(messages: List<SlackMessage>) {

        val slackMessages = messages.map {
            SlackMessageConverter.convert(it)
        }

        runBlocking {
            val deferreds: List<Deferred<Unit>> = slackMessages
                .map { message ->
                    async {
                        val (_, response, result) = Fuel.post(
                            "https://slack.com/api/chat.postMessage", listOf(
                                "token" to message.token,
                                "channel" to message.channel,
                                "text" to message.text
                            )
                        ).awaitStringResponseResult()

                        result.fold(
                            {},
                            { error -> throw Exception("${error.message}") }
                        )
                    }
                }

            deferreds.awaitAll()
        }
    }
}