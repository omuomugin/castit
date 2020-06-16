package domain.usecase

import domain.model.SlackMessage
import domain.model.error.Error
import domain.model.event.Event
import infra.command.PostSlackMessageCommandService
import infra.model.response.SlackPostMessageResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import presentation.handler.ErrorHandler
import presentation.handler.EventHandler

class PostMessageUseCase(
    private val eventHandler: EventHandler,
    private val errorHandler: ErrorHandler,
    private val service: PostSlackMessageCommandService
) {
    fun postMessages(messages: List<SlackMessage>) {
        runBlocking {
            val deferredList: List<Deferred<SlackPostMessageResponse>> = messages.map { message ->
                service.postMessageAsync(message = message)
            }

            deferredList.forEach { differed ->
                try {
                    differed.await().also { response ->
                        eventHandler.onEvent(Event.SlackPostCompleted(response.message))
                    }
                } catch (e: Exception) {
                    val errorMessage = e.message ?: "No error message"
                    errorHandler.onError(Error.SlackPostFailed(errorMessage))
                }
            }
        }
    }
}