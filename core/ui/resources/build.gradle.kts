
plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
    id("com.muhrifqii.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(compose.ui)
                api(projects.data.entities)
                api(compose.components.resources)
                implementation(compose.runtime)
            }
        }

        iosMain {
            dependencies {
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.assertk)
            }
        }

        jvmTest {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "com.muhrifqii.resources"

    sourceSets["main"].apply {
        res.srcDirs("src/androidMain/res")
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.muhrifqii.resources"
}
