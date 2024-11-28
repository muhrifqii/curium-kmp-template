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
                api(libs.kotlinx.datetime)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.core)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.notifications"
}
