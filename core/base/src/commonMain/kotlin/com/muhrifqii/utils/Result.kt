package com.muhrifqii.utils

import kotlinx.coroutines.CancellationException

inline fun <T> Result<T>.onError(
    consumer: (Throwable) -> Unit,
) {
    val err = exceptionOrNull()
    when {
        err is CancellationException -> throw err
        err != null -> consumer(err)
    }
}
