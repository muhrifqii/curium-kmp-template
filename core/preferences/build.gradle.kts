plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
                api(libs.multiplatformsettings.core)
                api(libs.multiplatformsettings.coroutines)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.core)
                implementation(libs.kotlininject.runtime)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.core.preferences"
}
