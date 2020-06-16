package domain.model.event

sealed class Event(val message: String) {
    class SlackPostCompleted(message: String) : Event(message)
}
