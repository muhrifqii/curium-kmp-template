package com.muhrifqii.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.muhrifqii.analytics.Analytics
import com.muhrifqii.settings.AppPreferences
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.PopResult
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.CoroutineScope
import me.tatarka.inject.annotations.Inject

@Inject
class DefaultMainContent(
    private val vm: (CoroutineScope) -> MainViewModel,
    private val circuit: Circuit,
    private val analytics: Analytics,
    private val preferences: AppPreferences
) : MainContent {

    @Composable
    override fun Content(
        backstack: SaveableBackStack,
        navigator: Navigator,
        modifier: Modifier
    ) {
        val coroutineScope = rememberCoroutineScope()
        remember { vm(coroutineScope) }


    }
}

private class AppNavigator(
    private val navigator: Navigator,
    private val backStack: SaveableBackStack,
) : Navigator {
//    private val logger by lazy { Logger.withTag("TiviNavigator") }

    override fun goTo(screen: Screen): Boolean {
//        logger.d { "goTo. Screen: $screen. Current stack: ${backStack.toList()}" }
        return navigator.goTo(screen)
    }

    override fun pop(result: PopResult?): Screen? {
//        logger.d { "pop. Current stack: ${backStack.toList()}" }
        return navigator.pop(result)
    }

    override fun resetRoot(
        newRoot: Screen,
        saveState: Boolean,
        restoreState: Boolean,
    ): ImmutableList<Screen> {
//        logger.d { "resetRoot: newRoot:$newRoot. Current stack: ${backStack.toList()}" }
        return navigator.resetRoot(newRoot, saveState, restoreState)
    }

    override fun peek(): Screen? = navigator.peek()

    override fun peekBackStack(): ImmutableList<Screen> = navigator.peekBackStack()
}
