package com.muhrifqii.core.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.muhrifqii.core.compose.ColorContrastConfig
import com.muhrifqii.core.compose.LocalPreferences
import com.muhrifqii.core.compose.extensions.collectAsState
import com.muhrifqii.settings.Theme

@Composable
fun AppTheme(
    useDarkColors: Boolean = shouldUseDarkColors(),
    useDynamicColors: Boolean = shouldUseDynamicColors(),
    colorContrastConfig: ColorContrastConfig = colorContrastConfiguration(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme(useDarkColors, useDynamicColors, colorContrastConfig),
        typography = AppTypography,
        content = content
    )
}

@Composable
fun shouldUseDarkColors(): Boolean {
    val themePreference = LocalPreferences.current.theme.collectAsState()
    return when (themePreference.value) {
        Theme.LIGHT, Theme.LIGHT_MEDIUM_CONTRAST, Theme.LIGHT_HIGH_CONTRAST -> false
        Theme.DARK, Theme.DARK_MEDIUM_CONTRAST, Theme.DARK_HIGH_CONTRAST -> true
        else -> isSystemInDarkTheme()
    }
}

@Composable
fun shouldUseDynamicColors(): Boolean {
    val state by LocalPreferences.current.useDynamicColor.collectAsState()
    return state
}

@Composable
fun colorContrastConfiguration(): ColorContrastConfig {
    val themePreference = LocalPreferences.current.theme.collectAsState()
    return when (themePreference.value) {
        Theme.LIGHT_MEDIUM_CONTRAST, Theme.DARK_MEDIUM_CONTRAST -> ColorContrastConfig.MEDIUM_CONTRAST
        Theme.LIGHT_HIGH_CONTRAST, Theme.DARK_HIGH_CONTRAST -> ColorContrastConfig.HIGH_CONTRAST
        else -> ColorContrastConfig.DEFAULT
    }
}
