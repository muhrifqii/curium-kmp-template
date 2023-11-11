package com.muhrifqii.build_logic

import com.muhrifqii.build_logic.project_extensions.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.gradle.android.cache-fix")
        }

        configureAndroid()
    }
}