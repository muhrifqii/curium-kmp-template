package com.muhrifqii.main

import com.muhrifqii.utils.launchOrThrow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class MainViewModel(
    @Assisted private val coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launchOrThrow {
        }
    }

    fun clear() {
        coroutineScope.cancel()
    }
}
