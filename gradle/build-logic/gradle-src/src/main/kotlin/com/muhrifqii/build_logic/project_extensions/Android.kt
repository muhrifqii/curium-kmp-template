package com.muhrifqii.build_logic.project_extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.util.Properties

fun Project.configureAndroid() {
    if (pluginManager.hasPlugin("com.android.library")) {
        android {
            compileSdk = Versions.COMPILE_SDK

            defaultConfig {
                minSdk = Versions.MIN_SDK
            }

//        compileOptions {
//            // https://developer.android.com/studio/write/java8-support
//            isCoreLibraryDesugaringEnabled = true
//        }
        }

//    dependencies {
//        // https://developer.android.com/studio/write/java8-support
//        "coreLibraryDesugaring"(libs.findLibrary("tools.desugarjdklibs").get())
//    }
    } else if (pluginManager.hasPlugin("com.android.application")) {
        androidApp {
            compileSdk = Versions.COMPILE_SDK

            defaultConfig {
                minSdk = Versions.MIN_SDK
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

//        compileOptions {
//            // https://developer.android.com/studio/write/java8-support
//            isCoreLibraryDesugaringEnabled = true
//        }
        }

//    dependencies {
//        // https://developer.android.com/studio/write/java8-support
//        "coreLibraryDesugaring"(libs.findLibrary("tools.desugarjdklibs").get())
//    }
    }

}

fun Project.publishToMavenLocal() {
    if (!pluginManager.hasPlugin("maven-publish") && !pluginManager.hasPlugin("com.android.library")) {
        return
    }

    extensions.configure(PublishingExtension::class.java) {
        publications {

        }
    }

//    android {
//        publishing {
//            publications {
//
//            }
//        }
//    }
}

private fun Project.android(action: LibraryExtension.() -> Unit) =
    extensions.configure(action)

private fun Project.androidApp(action: ApplicationExtension.() -> Unit) =
    extensions.configure(action)

object Versions {
    const val COMPILE_SDK = 35
    const val MIN_SDK = 24
}

fun File.orElse(file: File): File = when {
    exists() -> this
    else -> file
}

fun Project.readProperties(file: File): Properties = with(file) {
    val properties = Properties()
    inputStream().use { properties.load(it) }
    return properties
}
