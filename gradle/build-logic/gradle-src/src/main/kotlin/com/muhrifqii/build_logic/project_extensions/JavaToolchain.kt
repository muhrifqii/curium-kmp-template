package com.muhrifqii.build_logic.project_extensions

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure

fun Project.configureJavaToolchain() {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

private fun Project.java(action: JavaPluginExtension.() -> Unit) = extensions.configure(action)
