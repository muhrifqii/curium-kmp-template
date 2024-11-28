plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
    id("com.muhrifqii.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.ui.compose)
                implementation(projects.core.analytics)
                implementation(libs.circuit.foundation)
                implementation(libs.circuit.retained)
                implementation(libs.circuit.gestureNavigation)
                implementation(libs.circuit.overlay)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.main"
}
