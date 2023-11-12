package com.muhrifqii.logger

import co.touchlab.crashkios.crashlytics.enableCrashlytics
import co.touchlab.crashkios.crashlytics.setCrashlyticsUnhandledExceptionHook
import com.muhrifqii.base.apps.AppInitializer
import com.muhrifqii.base.injects.ApplicationScope
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

actual interface LoggerPlatformComponent {

    @get:Provides
    @get:ApplicationScope
    val crashLogReportingEnabler: CrashLogReportingEnabler

    @Provides
    @IntoSet
    fun provideCrashlyticsUnhandledExceptionInitializer(): AppInitializer =
        AppInitializer {
            enableCrashlytics()
            setCrashlyticsUnhandledExceptionHook()
        }
}
