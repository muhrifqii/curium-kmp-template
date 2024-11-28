package com.muhrifqii.curium

import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
    }
}

private fun setupStrictMode() {
    StrictMode.setThreadPolicy(
        ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build(),
    )
    StrictMode.setVmPolicy(
        VmPolicy.Builder()
            .detectLeakedSqlLiteObjects()
            .detectActivityLeaks()
            .detectLeakedClosableObjects()
            .detectLeakedRegistrationObjects()
            .detectFileUriExposure()
            .detectCleartextNetwork()
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    detectContentUriWithoutPermission()
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    detectCredentialProtectedWhileLocked()
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    detectIncorrectContextUse()
                    detectUnsafeIntentLaunch()
                }
            }
            .penaltyLog()
            .build(),
    )
}
