package com.muhrifqii.base.utils

import kotlin.reflect.KMutableProperty0

fun KMutableProperty0<Boolean>.toggle() {
    set(!get())
}

inline fun <T> tryWith(receiver: T, block: T.() -> Unit) {
    try {
        with(receiver, block)
    } catch (_: Exception) {
    }
}

inline fun <T> tryWith(
    receiver: T,
    block: T.() -> Unit,
    catchOnError: ((Exception) -> Unit)
) {
    try {
        with(receiver, block)
    } catch (err: Exception) {
        catchOnError(err)
    }
}
