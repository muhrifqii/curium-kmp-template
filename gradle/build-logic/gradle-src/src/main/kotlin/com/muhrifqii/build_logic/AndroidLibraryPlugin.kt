package com.muhrifqii.build_logic

import com.muhrifqii.build_logic.project_extensions.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.gradle.android.cache-fix")
            apply("maven-publish")
        }

        configureAndroid()
    }
}