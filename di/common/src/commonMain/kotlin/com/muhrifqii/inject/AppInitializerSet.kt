package com.muhrifqii.inject

import com.muhrifqii.apps.AppInitializer
import me.tatarka.inject.annotations.Inject

@Inject
class AppInitializerSet(
    private val initializerSet: Lazy<Set<AppInitializer>>
) : AppInitializer {
    override fun init() {
        for (initializer in initializerSet.value) {
            initializer.init()
        }
    }
}
