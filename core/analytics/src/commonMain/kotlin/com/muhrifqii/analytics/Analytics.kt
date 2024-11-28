package com.muhrifqii.analytics

interface Analytics {
    fun trackScreenView(
        name: String,
        arguments: Map<String, *>? = null,
    )

    fun setEnabled(enabled: Boolean)
}
