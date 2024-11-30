package com.muhrifqii.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember

const val ENABLE_LOG_COMPOSITIONS = false
data class LogCompositionsCounterRef(var count: Int)

/**
 * https://natalieb.substack.com/p/jetpack-compose-quick-tips-to-avoid
 * https://www.jetpackcompose.app/articles/how-can-I-debug-recompositions-in-jetpack-compose
 */
@Composable
@Suppress("NOTHING_TO_INLINE")
inline fun LogCompositions(tag: String, msg: String) {
    if (ENABLE_LOG_COMPOSITIONS) {
        val ref = remember { LogCompositionsCounterRef(0) }
        SideEffect { ref.count++ }
        println("[$tag] Compositions $msg: ${ref.count}")
    }
}
// Note the inline function which ensures that this function is essentially
// copied at the call site to ensure that its logging only recompositions from the
// original call site.
