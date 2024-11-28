import com.muhrifqii.build_logic.project_extensions.addKspDependencyForAllTargets

plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
    id("com.muhrifqii.compose")
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.di.common)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.di.defaults"
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}

addKspDependencyForAllTargets(libs.kotlininject.compiler)
