package com.muhrifqii.curium

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import com.muhrifqii.inject.ActivityComponent
import com.muhrifqii.inject.AndroidApplicationComponent
import com.muhrifqii.inject.create
import com.muhrifqii.permissions.bind
import com.slack.circuit.backstack.rememberSaveableBackStack
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val appComponent = AndroidApplicationComponent.from(this)
        val component = ActivityComponent.create(this, appComponent)

        component.permissionManager.bind(this)

        setContent {
        }
    }

}

private fun AndroidApplicationComponent.Companion.from(context: Context): AndroidApplicationComponent =
    (context.applicationContext as App).component
