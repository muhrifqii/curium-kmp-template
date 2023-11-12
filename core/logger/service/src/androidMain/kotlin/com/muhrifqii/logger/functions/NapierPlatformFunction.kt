package com.muhrifqii.logger.functions

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.muhrifqii.base.utils.tryWith
import com.muhrifqii.logger.Logger
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

actual fun Logger.setUserId(id: String) {
    tryWith(FirebaseCrashlytics.getInstance()) {
        setUserId(id)
    }
}

actual fun crashAntilog(): Antilog {
    return object : Antilog() {
        override fun isEnable(priority: LogLevel, tag: String?): Boolean = when (priority) {
            LogLevel.ERROR, LogLevel.WARNING, LogLevel.INFO -> true
            else -> false
        }

        override fun performLog(
            priority: LogLevel,
            tag: String?,
            throwable: Throwable?,
            message: String?
        ) {
            tryWith(FirebaseCrashlytics.getInstance()) {
                if (!message.isNullOrBlank()) {
                    log(message)
                }
                if (throwable != null) {
                    recordException(throwable)
                }
            }
        }
    }
}
