package com.muhrifqii.logger

import com.muhrifqii.apps.AppInitializer
import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

expect interface LoggerPlatformComponent

interface LoggerComponent : LoggerPlatformComponent {
    @Provides
    @ApplicationScope
    fun providesApplicationLogger(): Logger = NapierLogger

    @Provides
    @IntoSet
    fun providesApplicationLoggerInitializer(init: NapierLoggerInitializer): AppInitializer =
        init

    @Provides
    @IntoSet
    fun providesCrashReportingInitializer(init: CrashReportingInitializer): AppInitializer =
        init
}
