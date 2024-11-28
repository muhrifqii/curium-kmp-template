package com.muhrifqii.analytics

import com.muhrifqii.apps.AppInitializer
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

interface AnalyticsComponent: AnalyticsPlatformComponent  {
    @Provides
    @IntoSet
    fun provideAnalyticsInitializer(bind: AnalyticsInitializer): AppInitializer = bind
}
