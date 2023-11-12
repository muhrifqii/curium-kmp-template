package com.muhrifqii.logger

interface Logger {
    fun v(t: Throwable? = null, message: () -> String = { "" })

    fun d(t: Throwable? = null, message: () -> String = { "" })

    fun i(t: Throwable? = null, message: () -> String = { "" })

    fun w(t: Throwable? = null, message: () -> String = { "" })

    fun e(t: Throwable? = null, message: () -> String = { "" })

    fun wtf(t: Throwable? = null, message: () -> String = { "" })

}
