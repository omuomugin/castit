package presentation.runner

import domain.usecase.PostMessageUseCase
import infra.command.PostSlackMessageCommandServiceImpl
import presentation.converter.SlackMessageConverter
import presentation.model.Config

class CastRunner(
    private val config: Config
) : CommandRunner {

    override fun run(): String {

        val slackMessages = SlackMessageConverter.convert(config = config)

        PostMessageUseCase(PostSlackMessageCommandServiceImpl()).postMessages(slackMessages)

        return "Success"
    }
}