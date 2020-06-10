package domain.usecase

import domain.model.SlackMessage

class PostMessageUseCase {
    fun postMessages(slackMessage: SlackMessage) {
        // do something
        print("Post to Slack")
    }
}