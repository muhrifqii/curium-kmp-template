package com.muhrifqii.core.compose

import androidx.compose.runtime.staticCompositionLocalOf
import com.muhrifqii.settings.AppPreferences

val LocalPreferences = staticCompositionLocalOf<AppPreferences> {
    error("LocalPreferences not provided")
}
