package presentation.runner

import domain.usecase.PostMessageUseCase
import presentation.converter.SlackMessageConverter
import presentation.model.Config

class CastRunner(
    private val config: Config
) : CommandRunner {

    override fun run(): String {

        val slackMessage = SlackMessageConverter.convert(config = config)

        PostMessageUseCase().postMessages(slackMessage)

        return "Success"
    }
}