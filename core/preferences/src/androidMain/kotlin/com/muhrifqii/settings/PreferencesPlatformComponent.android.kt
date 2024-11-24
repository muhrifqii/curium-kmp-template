package com.muhrifqii.settings

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.muhrifqii.injects.ApplicationScope
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings
import me.tatarka.inject.annotations.Provides

actual interface PreferencesPlatformComponent {
    @ApplicationScope
    @Provides
    fun provideSettings(delegate: AppSharedPreferences): ObservableSettings
            = SharedPreferencesSettings(delegate)

    fun provideAppPreferences(
        context: Application
    ): AppSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}

typealias AppSharedPreferences = SharedPreferences
