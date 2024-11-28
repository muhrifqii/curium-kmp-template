package com.muhrifqii.analytics

import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Provides

actual interface AnalyticsPlatformComponent {
    @ApplicationScope
    @Provides
    fun provideFirebaseAnalytics(bind: AppFirebaseAnalytics): Analytics = bind
}
