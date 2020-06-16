package domain.model.error

sealed class Error(val message: String) {
    class SlackPostFailed(message: String) : Error(message)
}