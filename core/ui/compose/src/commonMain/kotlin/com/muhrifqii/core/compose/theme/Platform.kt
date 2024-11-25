package com.muhrifqii.core.compose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.muhrifqii.core.compose.ColorContrastConfig

@Composable
internal expect fun colorScheme(
    useDarkColors: Boolean,
    useDynamicColors: Boolean,
    colorContrastConfig: ColorContrastConfig
): ColorScheme
