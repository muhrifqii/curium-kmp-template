plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.composeCompiler.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("root") {
            id = "com.muhrifqii.buildLogic"
            implementationClass = "com.muhrifqii.build_logic.RootPlugin"
            version = "1.0.0-SNAPSHOT"
        }
        register("kotlinMultiplatform") {
            id = "com.muhrifqii.kotlin.multiplatform"
            implementationClass = "com.muhrifqii.build_logic.KotlinMultiplatformPlugin"
            version = "1.0.0-SNAPSHOT"
        }
        register("kotlinAndroid") {
            id = "com.muhrifqii.kotlin.android"
            implementationClass = "com.muhrifqii.build_logic.KotlinAndroidPlugin"
            version = "1.0.0-SNAPSHOT"
        }
        register("androidApplication") {
            id = "com.muhrifqii.android.application"
            implementationClass = "com.muhrifqii.build_logic.AndroidApplicationPlugin"
            version = "1.0.0-SNAPSHOT"
        }
        register("androidLibrary") {
            id = "com.muhrifqii.android.library"
            implementationClass = "com.muhrifqii.build_logic.AndroidLibraryPlugin"
            version = "1.0.0-SNAPSHOT"
        }
        register("androidTest") {
            id = "com.muhrifqii.android.test"
            implementationClass = "com.muhrifqii.build_logic.AndroidTestPlugin"
            version = "1.0.0-SNAPSHOT"
        }
        register("compose") {
            id = "com.muhrifqii.compose"
            implementationClass = "com.muhrifqii.build_logic.ComposePlugin"
            version = "1.0.0-SNAPSHOT"
        }
    }
}
