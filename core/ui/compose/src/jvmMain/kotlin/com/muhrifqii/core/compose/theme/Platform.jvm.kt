package com.muhrifqii.core.compose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.muhrifqii.core.compose.ColorContrastConfig

@Composable
internal actual fun colorScheme(
    useDarkColors: Boolean,
    useDynamicColors: Boolean,
    colorContrastConfig: ColorContrastConfig
): ColorScheme = when {
    useDarkColors -> {
        when (colorContrastConfig) {
            ColorContrastConfig.DEFAULT -> AppDarkColorScheme
            ColorContrastConfig.MEDIUM_CONTRAST -> AppMediumContrastDarkColorScheme
            ColorContrastConfig.HIGH_CONTRAST -> AppHighContrastDarkColorScheme
        }
    }
    else -> {
        when (colorContrastConfig) {
            ColorContrastConfig.DEFAULT -> AppLightColorScheme
            ColorContrastConfig.MEDIUM_CONTRAST -> AppMediumContrastLightColorScheme
            ColorContrastConfig.HIGH_CONTRAST -> AppHighContrastLightColorScheme
        }
    }
}
