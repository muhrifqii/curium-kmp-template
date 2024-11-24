package com.muhrifqii.settings

import kotlinx.coroutines.flow.Flow

interface Preference<T> {
    val defaultValue: T

    val flow: Flow<T>
    suspend fun get(): T
    suspend fun set(value: T)

}

suspend fun Preference<Boolean>.toggle() = set(!get())
