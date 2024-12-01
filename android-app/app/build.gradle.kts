plugins {
    id("com.muhrifqii.android.application")
    id("com.muhrifqii.kotlin.android")
    id("com.muhrifqii.compose")
}

android {
    namespace = "com.muhrifqii.curium"

    defaultConfig {
        applicationId = "com.muhrifqii.curium"
        versionCode = 1
        versionName = "0.0.1"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            versionNameSuffix = "-alpha"
        }
    }

    flavorDimensions += "mode"
    productFlavors {
        create("default") {
            dimension = "mode"
        }
    }
}

dependencies {
    defaultImplementation(projects.di.default)

    implementation(libs.androidx.activity.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.androidx.splashscreen)
//    implementation(libs.leakCanary)
    implementation(libs.kotlin.coroutines.android)

    implementation(project.dependencies.platform(libs.google.firebase.bom))
    implementation(libs.google.firebase.crashlytics)

    androidTestImplementation(libs.androidx.uiautomator)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
}

if (file("google-services.json").exists()) {
    apply(plugin = libs.plugins.gms.googleServices.get().pluginId)
    apply(plugin = libs.plugins.firebase.crashlytics.get().pluginId)
}

fun DependencyHandler.qaImplementation(dependencyNotation: Any) =
    add("qaImplementation", dependencyNotation)

fun DependencyHandler.defaultImplementation(dependencyNotation: Any) =
    add("defaultImplementation", dependencyNotation)
