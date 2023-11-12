package com.muhrifqii.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.muhrifqii.base.injects.ApplicationScope
import com.muhrifqii.base.utils.tryWith
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
