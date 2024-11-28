package com.muhrifqii.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.runtime.Navigator

interface MainContent {
    @Composable
    fun Content(
        backstack: SaveableBackStack,
        navigator: Navigator,
        modifier: Modifier,
    )
}
