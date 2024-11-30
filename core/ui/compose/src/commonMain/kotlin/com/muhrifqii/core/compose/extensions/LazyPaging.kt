@file:Suppress("NOTHING_TO_INLINE")
package com.muhrifqii.core.compose.extensions

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.muhrifqii.core.compose.ComposableMessage
import com.muhrifqii.core.compose.toComposableMessage


inline fun CombinedLoadStates.appendErrorOrNull(): ComposableMessage? {
    return (append as? LoadState.Error)?.error?.toComposableMessage()
}

inline fun CombinedLoadStates.prependErrorOrNull(): ComposableMessage? {
    return (prepend as? LoadState.Error)?.error?.toComposableMessage()
}

inline fun CombinedLoadStates.refreshErrorOrNull(): ComposableMessage? {
    return (refresh as? LoadState.Error)?.error?.toComposableMessage()
}
