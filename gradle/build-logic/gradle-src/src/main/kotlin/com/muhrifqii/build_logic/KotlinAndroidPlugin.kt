package com.muhrifqii.build_logic

import com.muhrifqii.build_logic.project_extensions.configureJavaToolchain
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.android")
        }
        configureJavaToolchain()
    }
}
