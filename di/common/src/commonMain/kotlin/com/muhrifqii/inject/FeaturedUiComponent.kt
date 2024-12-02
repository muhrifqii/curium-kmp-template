package com.muhrifqii.inject

import com.muhrifqii.injects.ActivityScope
import com.muhrifqii.main.MainUiComponent
import com.muhrifqii.main.MainContent
import com.muhrifqii.permissions.PermissionManager
import com.slack.circuit.foundation.Circuit
import me.tatarka.inject.annotations.Provides

interface FeaturedUiComponent :
    MainUiComponent {

    val permissionManager: PermissionManager
    val mainContent: MainContent

    @Provides
    @ActivityScope
    fun provideCircuit(): Circuit =
        Circuit.Builder()
            .build()
}
