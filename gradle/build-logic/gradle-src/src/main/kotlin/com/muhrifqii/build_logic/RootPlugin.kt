package com.muhrifqii.build_logic

import com.muhrifqii.build_logic.project_extensions.configureSpotless
import org.gradle.api.Plugin
import org.gradle.api.Project

class RootPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configureSpotless()
    }
}