package com.muhrifqii.core.compose

import com.slack.circuit.runtime.screen.Screen

abstract class CuriumScreen(val name: String): Screen {
    open val args: Map<String, Any>? = null
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class ParcelizeScreen
