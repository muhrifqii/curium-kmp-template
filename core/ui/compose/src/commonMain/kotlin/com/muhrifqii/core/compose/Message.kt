package com.muhrifqii.core.compose

import com.benasher44.uuid.uuid4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

data class ComposableMessage(
    val message: String,
    val id: String = uuid4().toString()
)

fun Throwable.toComposableMessage() = ComposableMessage(
    message = message ?: "Error occured: $this"
)

class ComposableMessageManager {
    private val _message = MutableStateFlow(emptyList<ComposableMessage>())

    val message = _message.map { it.firstOrNull() }.distinctUntilChanged()

    fun emitMessage(message: ComposableMessage) {
        _message.update { it + message }
    }

    fun emitMessage(error: Throwable) {
        _message.update { it + error.toComposableMessage() }
    }

    fun clearMessage(id: String) {
        _message.update { messages ->
            messages.filterNot { it.id == id }
        }
    }
}
