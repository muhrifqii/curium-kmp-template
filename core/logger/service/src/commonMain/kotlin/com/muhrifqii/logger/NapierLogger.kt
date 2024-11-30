package com.muhrifqii.logger

import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

object NapierLogger : Logger {
    override fun log(
        priority: Logger.LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String
    ) {
        Napier.log(
            priority = priority.toNapier(),
            tag = tag,
            throwable = throwable,
            message = message
        )
    }
}

private val napierLogLevel: Map<Logger.LogLevel, LogLevel> = mapOf(
    Logger.LogLevel.ASSERT to LogLevel.ASSERT,
    Logger.LogLevel.DEBUG to LogLevel.DEBUG,
    Logger.LogLevel.VERBOSE to LogLevel.VERBOSE,
    Logger.LogLevel.INFO to LogLevel.INFO,
    Logger.LogLevel.WARN to LogLevel.WARNING,
    Logger.LogLevel.ERROR to LogLevel.ERROR,
)

internal fun Logger.LogLevel.toNapier(): LogLevel = napierLogLevel[this]!!
