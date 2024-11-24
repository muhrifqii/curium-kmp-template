package com.muhrifqii.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
suspend inline fun <T> Collection<T>.parallelForEach(
    concurrency: Int = DEFAULT_CONCURRENCY,
    crossinline action: suspend (value: T) -> Unit,
) {
    asFlow().flatMapMerge(concurrency = concurrency) { item ->
        flow {
            action(item)
            emit(Unit)
        }
    }.collect()
}
