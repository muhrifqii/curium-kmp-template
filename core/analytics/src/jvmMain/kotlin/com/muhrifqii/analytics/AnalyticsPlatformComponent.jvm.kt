package com.muhrifqii.analytics

import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Provides

actual interface AnalyticsPlatformComponent {
    @Provides
    @ApplicationScope
    fun provideFirebaseAnalytics(): Analytics = object : Analytics {
        override fun trackScreenView(name: String, arguments: Map<String, *>?) = Unit
        override fun setEnabled(enabled: Boolean) = Unit
    }
}
