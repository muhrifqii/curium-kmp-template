package com.muhrifqii.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import com.slack.circuit.retained.rememberRetained
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

@Composable
fun rememberRetainedCoroutineScope(): CoroutineScope =
    rememberRetained("coroutine_scope") {
        RememberObserverHolder(
            value = CoroutineScope(Dispatchers.Main + Job()),
            onDestroy = CoroutineScope::cancel
        )
    }.value


internal class RememberObserverHolder<T>(
    val value: T,
    private val onDestroy: (T) -> Unit,
) : RememberObserver {
    override fun onAbandoned() {
        onDestroy(value)
    }

    override fun onForgotten() {
        onDestroy(value)
    }

    override fun onRemembered() = Unit
}
