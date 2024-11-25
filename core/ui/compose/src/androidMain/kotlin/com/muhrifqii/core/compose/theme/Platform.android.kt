package com.muhrifqii.core.compose.theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.muhrifqii.core.compose.ColorContrastConfig

@Composable
internal actual fun colorScheme(
    useDarkColors: Boolean,
    useDynamicColors: Boolean,
    colorContrastConfig: ColorContrastConfig
): ColorScheme = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && useDynamicColors && useDarkColors -> {
        dynamicDarkColorScheme(LocalContext.current)
    }

    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && useDynamicColors && !useDarkColors -> {
        dynamicLightColorScheme(LocalContext.current)
    }

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
