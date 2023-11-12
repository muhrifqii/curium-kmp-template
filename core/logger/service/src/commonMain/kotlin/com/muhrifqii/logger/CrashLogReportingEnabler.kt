package com.muhrifqii.logger

fun interface CrashLogReportingEnabler {
    operator fun invoke(enabled: Boolean)
}
