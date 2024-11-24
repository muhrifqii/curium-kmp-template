package com.muhrifqii.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.muhrifqii.injects.ApplicationScope
import com.muhrifqii.utils.tryWith
import me.tatarka.inject.annotations.Provides

actual interface LoggerPlatformComponent {
    @Provides
    @ApplicationScope
    fun bindCrashLogReportingEnabler(): CrashLogReportingEnabler =
        CrashLogReportingEnabler {
            tryWith(FirebaseCrashlytics.getInstance()) {
                setCrashlyticsCollectionEnabled(it)
            }
        }
}
