package com.muhrifqii.permissions

import androidx.activity.ComponentActivity

fun PermissionManager.bind(activity: ComponentActivity) {
    this as MokoPermissionManager
    controller.bind(activity)
}
