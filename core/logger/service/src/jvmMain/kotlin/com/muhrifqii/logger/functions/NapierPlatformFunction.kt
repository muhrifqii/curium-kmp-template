package com.muhrifqii.logger.functions

import com.muhrifqii.logger.Logger
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.LogLevel

actual fun Logger.setUserId(id: String) {
}

actual fun crashAntilog(): Antilog {
    return object : Antilog() {
        private val debugOwner = DebugAntilog()

        override fun isEnable(priority: LogLevel, tag: String?): Boolean = when (priority) {
            LogLevel.ERROR, LogLevel.WARNING -> true
            else -> false
        }

        override fun performLog(
            priority: LogLevel,
            tag: String?,
            throwable: Throwable?,
            message: String?
        ) {
            debugOwner.log(priority, tag, throwable, message)
        }
    }
}
