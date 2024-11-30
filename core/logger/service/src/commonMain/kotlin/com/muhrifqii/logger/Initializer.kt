package com.muhrifqii.logger

import com.muhrifqii.apps.AppInitializer
import com.muhrifqii.apps.ApplicationBuildFlavor
import com.muhrifqii.apps.ApplicationInfo
import com.muhrifqii.logger.functions.crashAntilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import me.tatarka.inject.annotations.Inject

@Inject
class CrashReportingInitializer(
    private val enabler: CrashLogReportingEnabler,
) : AppInitializer {
    override fun init() {
        enabler(false)
    }
}

@Inject
class NapierLoggerInitializer(
    private val applicationInfo: ApplicationInfo,
) : AppInitializer {
    override fun init() {
        if (applicationInfo.debugBuild || applicationInfo.flavor == ApplicationBuildFlavor.QA) {
            Napier.base(DebugAntilog())
        } else {
            Napier.base(crashAntilog())
        }
    }
}
