package com.muhrifqii.logger

interface Logger {

     fun log(
        priority: LogLevel,
        tag: String? = null,
        throwable: Throwable? = null,
        message: String
    )

    fun v(t: Throwable? = null, tag: String? = null, message: () -> String) {
        log(LogLevel.VERBOSE, tag, t, message())
    }

    fun d(t: Throwable? = null, tag: String? = null, message: () -> String) {
        log(LogLevel.DEBUG, tag, t, message())
    }

    fun i(t: Throwable? = null, tag: String? = null, message: () -> String) {
        log(LogLevel.INFO, tag, t, message())
    }

    fun w(t: Throwable? = null, tag: String? = null, message: () -> String) {
        log(LogLevel.WARN, tag, t, message())
    }

    fun e(t: Throwable? = null, tag: String? = null, message: () -> String) {
        log(LogLevel.ERROR, tag, t, message())
    }

    fun wtf(t: Throwable? = null, tag: String? = null, message: () -> String) {
        log(LogLevel.ASSERT, tag, t, message())
    }

    enum class LogLevel {
        VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT
    }
}

