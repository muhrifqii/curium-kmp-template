plugins {
    id("com.muhrifqii.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.base)
                api(libs.kotlinx.datetime)
            }
        }
    }
}
