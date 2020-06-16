package presentation.handler

import domain.model.event.Event

interface EventHandler {
    fun onEvent(event: Event)
}