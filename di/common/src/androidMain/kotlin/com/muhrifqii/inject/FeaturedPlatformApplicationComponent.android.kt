package com.muhrifqii.inject

import android.app.Application
import android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE
import android.os.Build
import com.muhrifqii.apps.ApplicationBuildFlavor
import com.muhrifqii.apps.ApplicationBuildPlatform
import com.muhrifqii.apps.ApplicationInfo
import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Provides

actual interface FeaturedPlatformApplicationComponent {
    @ApplicationScope
    @Provides
    fun provideApplicationInfo(
        application: Application,
        flavor: ApplicationBuildFlavor,
    ): ApplicationInfo {
        val packageManager = application.packageManager
        val applicationInfo = packageManager.getApplicationInfo(application.packageName, 0)
        val packageInfo = packageManager.getPackageInfo(application.packageName, 0)
        val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode
        } else {
             packageInfo.versionCode.toLong()
        }

        return ApplicationInfo(
            packageName = application.packageName,
            debugBuild = (applicationInfo.flags and FLAG_DEBUGGABLE) != 0,
            flavor = flavor,
            versionName = packageInfo.versionName!!,
            versionCode = versionCode,
            cachePath = { application.cacheDir.absolutePath },
            platform = ApplicationBuildPlatform.ANDROID,
        )
    }
}
