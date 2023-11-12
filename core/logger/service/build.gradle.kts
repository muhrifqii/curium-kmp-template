plugins {
    id("com.muhrifqii.android.library")
    id("com.muhrifqii.kotlin.multiplatform")
}

kotlin {
   sourceSets {

       val commonMain by getting {
           dependencies {
               api(projects.core.base)
               api(projects.core.logger.api)
//               implementation(projects.core.preferences)
               implementation(libs.kotlin.coroutines.core)
               implementation(libs.kotlininject.runtime)
               implementation(libs.napier)
           }
       }
       val androidMain by getting {
           dependencies {
               implementation(project.dependencies.platform(libs.google.firebase.bom))
               implementation(libs.google.firebase.crashlytics)
           }
       }
       val iosMain by getting {
           dependencies {
               implementation(libs.crashkios.crashlytics)
           }
       }
   }
}

android {
    namespace = "com.muhrifqii.core.logger"
}

