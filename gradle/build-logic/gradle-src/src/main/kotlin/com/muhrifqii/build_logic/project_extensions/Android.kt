package com.muhrifqii.build_logic.project_extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.HasUnitTestBuilder
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureAndroid() {
    if (pluginManager.hasPlugin("com.android.library")) {
        androidLib {
            compileSdk = Versions.COMPILE_SDK

            defaultConfig {
                minSdk = Versions.MIN_SDK
            }
        }
    } else if (pluginManager.hasPlugin("com.android.application")) {
        androidApp {
            compileSdk = Versions.COMPILE_SDK

            defaultConfig {
                minSdk = Versions.MIN_SDK
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            packaging {
                resources {
                    excludes += setOf(
                        "/META-INF/{AL2.0,LGPL2.1}",
                        // Exclude AndroidX version files
                        "META-INF/*.version",
                        // Exclude consumer proguard files
                        "META-INF/proguard/*",
                        // Exclude the Firebase/Fabric/other random properties files
                        "/*.properties",
                        "fabric/*.properties",
                        "META-INF/*.properties",
                        // License files
                        "LICENSE*",
                        // Exclude Kotlin unused files
                        "META-INF/**/previous-compilation-data.bin",
                    )
                }
            }
        }
    }

    androidComponents {
        beforeVariants(selector().withBuildType("release")) { variantBuilder ->
            (variantBuilder as? HasUnitTestBuilder)?.apply {
                enableUnitTest = false
            }
        }

        onVariants(selector().withBuildType("release")) { variant ->
            variant.packaging.resources.run {
                // Exclude AndroidX version files. We only do this in the release build so that
                // Layout Inspector continues to work for debug
                excludes.add("META-INF/*.version")
                // Exclude the Firebase/Fabric/other random properties files
                excludes.addAll("/*.properties", "META-INF/*.properties")
            }
        }
    }

}

private fun Project.androidLib(action: LibraryExtension.() -> Unit) =
    extensions.configure(action)

private fun Project.androidApp(action: ApplicationExtension.() -> Unit) =
    extensions.configure(action)

private fun Project.androidComponents(action: AndroidComponentsExtension<*, *, *>.() -> Unit) =
    extensions.configure(AndroidComponentsExtension::class.java, action)
