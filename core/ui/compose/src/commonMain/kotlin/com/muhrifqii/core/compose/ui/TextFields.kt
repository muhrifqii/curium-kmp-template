package com.muhrifqii.core.compose.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ClickableReadOnlyOutlinedTextField(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
) {
    val borderColor = MaterialTheme.colorScheme.primary.copy(
        alpha = if (value.isNotEmpty()) 1f else 0.4f,
    )

    androidx.compose.material3.Surface(
        onClick = onClick,
        border = BorderStroke(1.dp, borderColor),
        shape = shape,
        modifier = modifier,
    ) {
        Box(Modifier.padding(16.dp)) {
            if (value.isNotEmpty()) {
                Text(text = value)
            } else {
                val disabledText = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                CompositionLocalProvider(
                    LocalTextStyle provides LocalTextStyle.current.copy(color = disabledText),
                ) {
                    label?.invoke()
                }
            }
        }
    }
}
