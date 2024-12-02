plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.base)
                implementation(projects.core.logger.api)
            }
        }

        val mokoImplMain by creating {
            dependsOn(commonMain.get())

            dependencies {
                implementation(libs.moko.permissions.core)
            }
        }

        androidMain {
            dependsOn(mokoImplMain)

            dependencies {
                implementation(libs.androidx.activity.activity)
            }
        }

        iosMain {
            dependsOn(mokoImplMain)
        }
    }
}

android {
    namespace = "com.muhrifqii.permissions"
}
