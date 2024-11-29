package com.muhrifqii.settings

import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Provides

interface PreferencesComponent : PreferencesPlatformComponent {
    val preferences: AppPreferences

    @ApplicationScope
    @Provides
    fun providePreferences(bind: AppPreferenceImpl): AppPreferences = bind
}
