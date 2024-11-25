package com.muhrifqii.core.compose.extensions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.muhrifqii.core.compose.Layout

inline fun LazyListScope.itemSpacer(height: Dp) {
    item {
        Spacer(
            Modifier
                .height(height)
                .fillParentMaxWidth(),
        )
    }
}

inline fun LazyListScope.gutterSpacer() {
    item {
        Spacer(
            Modifier
                .height(Layout.gutter)
                .fillParentMaxWidth(),
        )
    }
}

inline fun LazyGridScope.fullSpanItem(
    key: Any? = null,
    contentType: Any? = null,
    noinline content: @Composable LazyGridItemScope.() -> Unit,
) {
    item(
        key = key,
        span = { GridItemSpan(maxLineSpan) },
        contentType = contentType,
        content = content,
    )
}
