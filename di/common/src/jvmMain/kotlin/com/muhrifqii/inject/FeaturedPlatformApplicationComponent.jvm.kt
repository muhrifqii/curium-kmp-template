package com.muhrifqii.inject

import com.muhrifqii.apps.ApplicationBuildFlavor
import com.muhrifqii.apps.ApplicationBuildPlatform
import com.muhrifqii.apps.ApplicationInfo
import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Provides
import java.io.File
import java.util.prefs.Preferences

actual interface FeaturedPlatformApplicationComponent {
    @ApplicationScope
    @Provides
    fun provideApplicationId(
        flavor: ApplicationBuildFlavor,
    ): ApplicationInfo = ApplicationInfo(
        packageName = "com.muhrifqii.curium",
        debugBuild = true,
        flavor = flavor,
        versionName = "0.1.0",
        versionCode = 1,
        cachePath = { getCacheDir().absolutePath },
        platform = ApplicationBuildPlatform.DESKTOP,
    )

    @ApplicationScope
    @Provides
    fun providePreferences(): Preferences = Preferences.userRoot().node("com.muhrifqii.curium")
}

private fun getCacheDir(): File = when (currentOperatingSystem) {
    OperatingSystem.Windows -> File(System.getenv("AppData"), "curium/cache")
    OperatingSystem.Linux -> File(System.getProperty("user.home"), ".cache/curium")
    OperatingSystem.MacOS -> File(System.getProperty("user.home"), "Library/Caches/curium")
    else -> throw IllegalStateException("Unsupported operating system")
}

internal enum class OperatingSystem {
    Windows,
    Linux,
    MacOS,
    Unknown,
}

private val currentOperatingSystem: OperatingSystem
    get() {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> OperatingSystem.Windows
            os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
                OperatingSystem.Linux
            }

            os.contains("mac") -> OperatingSystem.MacOS
            else -> OperatingSystem.Unknown
        }
    }
