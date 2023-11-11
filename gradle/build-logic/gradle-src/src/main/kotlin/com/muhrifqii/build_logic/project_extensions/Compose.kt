package com.muhrifqii.build_logic.project_extensions

import org.gradle.api.Project

fun Project.configureCompose() {
    val composeVersion = libs.findVersion("compose-multiplatform")
        .get().requiredVersion

    configurations.configureEach {
        resolutionStrategy.eachDependency {
            val group = requested.group
            if (group.startsWith("org.jetbrains.compose") && !group.endsWith("compiler")) {
                useVersion(composeVersion)
            }
        }
    }
}
