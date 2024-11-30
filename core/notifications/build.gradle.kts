plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.base)
                implementation(projects.core.ui.resources)
                implementation(projects.core.logger.api)
                api(libs.kotlinx.datetime)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.core)
                implementation(libs.androidx.datastore)
                implementation(projects.data.protobuf)
                implementation(projects.core.logger.service)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.notifications"
}
