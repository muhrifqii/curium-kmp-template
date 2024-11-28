package com.muhrifqii.inject

import com.muhrifqii.apps.ApplicationBuildFlavor
import me.tatarka.inject.annotations.Provides

interface DefaultApplicationComponent {
    @Provides
    fun provideFlavor(): ApplicationBuildFlavor = ApplicationBuildFlavor.Default
}
