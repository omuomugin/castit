package presentation.runner

import domain.model.error.Error
import domain.model.event.Event
import domain.usecase.PostMessageUseCase
import infra.command.PostSlackMessageCommandService
import presentation.converter.SlackMessageConverter
import presentation.handler.ErrorHandler
import presentation.handler.EventHandler
import presentation.model.Config
import view.output.MessageBucket

class CastRunner(
    private val config: Config,
    private val messageBucket: MessageBucket,
    private val service: PostSlackMessageCommandService
) : CommandRunner, EventHandler, ErrorHandler {

    override fun run(): String {

        val slackMessages = SlackMessageConverter.convert(config = config)
        val useCase = PostMessageUseCase(
            eventHandler = this,
            errorHandler = this,
            service = service
        )

        useCase.postMessages(slackMessages)

        return "[CAST-IT] Finish Casting Messaging to Slack"
    }

    override fun onEvent(event: Event) {
        messageBucket.outPutMessage("[CAST-IT][Event] ${event.message}")
    }

    override fun onError(error: Error) {
        messageBucket.outPutMessage("[CAST-IT][Error] ${error.message}")
    }
}