plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
    id("com.muhrifqii.compose")
}

kotlin {
    sourceSets {

        commonMain {
            dependencies {
                api(projects.core.preferences)
                api(libs.circuit.foundation)

                api(libs.lyricist.library)
                api(projects.core.ui.resources)

                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                api(compose.material3)
                api(libs.compose.material3.windowsizeclass)
                implementation(compose.animation)

                implementation(libs.androidx.collection)

                implementation(libs.uuid)
                api(libs.paging.common)
            }
        }

        val commonJvm by creating {
            dependsOn(commonMain.get())
        }

        androidMain {
            dependsOn(commonJvm)
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
        }

        jvmMain {
            dependsOn(commonJvm)
        }
    }
}

android {
    namespace = "com.muhrifqii.core.compose"
}
