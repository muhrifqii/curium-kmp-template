plugins {
    id("com.muhrifqii.kotlin.multiplatform")
}

kotlin {
   sourceSets {
       val commonMain by getting {
           dependencies {
               api(libs.kotlin.coroutines.core)
           }
       }
   }
}
