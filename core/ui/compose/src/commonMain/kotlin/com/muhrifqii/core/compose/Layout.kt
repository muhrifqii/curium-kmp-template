package com.muhrifqii.core.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Layout {
    val bodyMargin: Dp
        @Composable get() = when (LocalWindowSizeClass.current.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 16.dp
            WindowWidthSizeClass.Medium -> 32.dp
            WindowWidthSizeClass.Expanded -> 64.dp
            else -> 16.dp
        }
    val gutter: Dp
        @Composable get() = when (LocalWindowSizeClass.current.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 8.dp
            WindowWidthSizeClass.Medium -> 16.dp
            else -> 24.dp
        }

    val columns: Int
        @Composable get() = when (LocalWindowSizeClass.current.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 4
            WindowWidthSizeClass.Medium -> 8
            else -> 12
        }
}

fun Modifier.bodyWidth() = fillMaxWidth()
    .composed {
        windowInsetsPadding(
            WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
        )
    }
