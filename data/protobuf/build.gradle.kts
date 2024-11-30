plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
    alias(libs.plugins.wire)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.base)
                api(libs.wire.runtime)
            }
        }
    }
}

wire {
    kotlin {}
}

android {
    namespace = "com.muhrifqii.data.protobuf"
}
