package com.muhrifqii.build_logic.project_extensions

import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType


fun Project.addKspDependencyForAllTargets(dependencyNotation: Any) =
    addKspDependencyForAllTargets("", dependencyNotation)
fun Project.addKspTestDependencyForAllTargets(dependencyNotation: Any) =
    addKspDependencyForAllTargets("Test", dependencyNotation)

private fun Project.addKspDependencyForAllTargets(
    configurationNameSuffix: String,
    dependencyNotation: Any,
) {
    val kmpExtension = extensions.getByType(KotlinMultiplatformExtension::class.java)
    dependencies {
        kmpExtension.targets
            .asSequence()
            .filter {
                // Don't add KSP for common target, only final platforms
                it.platformType != KotlinPlatformType.common
            }
            .forEach {
                add(
                    "ksp${it.targetName.capitalized()}$configurationNameSuffix",
                    dependencyNotation,
                )
            }
    }
}