package com.muhrifqii.core.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.muhrifqii.settings.Preference

@Composable
fun <T> Preference<T>.collectAsState() = flow.collectAsState(defaultValue)
