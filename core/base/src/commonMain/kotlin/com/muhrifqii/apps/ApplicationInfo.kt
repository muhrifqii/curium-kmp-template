package com.muhrifqii.apps

data class ApplicationInfo(
    val packageName: String,
    val debugBuild: Boolean,
    val flavor: ApplicationBuildFlavor,
    val versionName: String,
    val versionCode: Long,
)
