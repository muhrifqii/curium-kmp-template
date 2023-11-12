package com.muhrifqii.logger

import com.muhrifqii.base.apps.AppInitializer
import com.muhrifqii.base.apps.ApplicationBuildFlavor
import com.muhrifqii.base.apps.ApplicationInfo
import com.muhrifqii.logger.functions.crashAntilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import me.tatarka.inject.annotations.Inject

@Inject
class CrashReportingInitializer(
    private val enabler: CrashLogReportingEnabler,
) : AppInitializer {
    override fun init() {
        enabler(true)
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
