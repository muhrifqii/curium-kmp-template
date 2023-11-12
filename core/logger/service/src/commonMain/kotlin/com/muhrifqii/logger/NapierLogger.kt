package com.muhrifqii.logger

import io.github.aakira.napier.Napier

internal object NapierLogger : Logger {

    override fun v(t: Throwable?, message: () -> String) {
        Napier.v(throwable = t, message = message)
    }

    override fun d(t: Throwable?, message: () -> String) {
        Napier.d(throwable = t, message = message)
    }

    override fun i(t: Throwable?, message: () -> String) {
        Napier.i(throwable = t, message = message)
    }

    override fun w(t: Throwable?, message: () -> String) {
        Napier.w(throwable = t, message = message)
    }

    override fun e(t: Throwable?, message: () -> String) {
        Napier.e(throwable = t, message = message)
    }

    override fun wtf(t: Throwable?, message: () -> String) {
        Napier.wtf(throwable = t, message = message)
    }
}
