package com.muhrifqii.settings

interface AppPreferences {
    val theme: Preference<Theme>
    val useDynamicColor: Preference<Boolean>

    val reportAnalytics: Preference<Boolean>
    val notificationEnabled: Preference<Boolean>
}
