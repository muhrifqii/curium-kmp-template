package com.muhrifqii.logger

import com.muhrifqii.base.injects.ApplicationScope
import me.tatarka.inject.annotations.Provides

actual interface LoggerPlatformComponent {
    @Provides
    @ApplicationScope
    fun bindCrashLogReportingEnabler(): CrashLogReportingEnabler =
        CrashLogReportingEnabler {
            // todo: crash reporting without crashlytics
        }
}
