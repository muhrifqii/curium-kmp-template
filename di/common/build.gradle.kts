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
                api(projects.core.base)
                api(projects.core.analytics)
                api(projects.core.logger.api)
                api(projects.core.logger.service)
                api(projects.core.notifications)
                api(projects.core.permissions)
                api(projects.core.preferences)
                api(projects.core.ui.compose)
                api(projects.core.ui.resources)

                api(projects.feature.main)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.di.common"
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}

addKspDependencyForAllTargets(libs.kotlininject.compiler)
