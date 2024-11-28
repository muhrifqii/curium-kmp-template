package com.muhrifqii.analytics

import com.muhrifqii.apps.AppInitializer
import com.muhrifqii.injects.ApplicationCoroutineScope
import com.muhrifqii.settings.AppPreferences
import com.muhrifqii.utils.launchOrThrow
import me.tatarka.inject.annotations.Inject

@Inject
class AnalyticsInitializer(
    private val preference: Lazy<AppPreferences>,
    private val analytics: Lazy<Analytics>,
    private val scope: ApplicationCoroutineScope
) : AppInitializer {
    override fun init() {
        scope.launchOrThrow {
            preference.value.reportAnalytics.flow.collect {
                analytics.value.setEnabled(it)
            }
        }
    }
}
