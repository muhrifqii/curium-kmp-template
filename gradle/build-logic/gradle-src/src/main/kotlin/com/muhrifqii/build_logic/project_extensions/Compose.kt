package com.muhrifqii.build_logic.project_extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compiler.html#migrating-a-compose-multiplatform-project
 */
fun Project.configureCompose() {
    val composeVersion = libs.findVersion("compose-multiplatform")
        .get().requiredVersion

    composeCompiler {
        includeSourceInformation.set(true)
    }

    configurations.configureEach {
        resolutionStrategy.eachDependency {
            val group = requested.group
            if (group.startsWith("org.jetbrains.compose") && !group.endsWith("compiler")) {
                useVersion(composeVersion)
            }
        }
    }
}

fun Project.composeCompiler(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
    extensions.configure<ComposeCompilerGradlePluginExtension>(block)
}
