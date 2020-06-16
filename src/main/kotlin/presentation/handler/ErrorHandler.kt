package presentation.handler

import domain.model.error.Error

interface ErrorHandler {
    fun onError(error: Error)
}
