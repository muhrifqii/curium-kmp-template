plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
                api(projects.core.preferences)
            }
        }

        androidMain {
            dependencies {
                implementation(project.dependencies.platform(libs.google.firebase.bom))
                implementation(libs.google.firebase.analytics)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.core.analytics"
}
