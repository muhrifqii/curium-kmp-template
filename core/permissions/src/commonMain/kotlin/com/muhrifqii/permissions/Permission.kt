package com.muhrifqii.permissions

enum class Permission {
    CAMERA,
    GALLERY,
    STORAGE,
    WRITE_STORAGE,
    LOCATION,
    COARSE_LOCATION,
    BACKGROUND_LOCATION,
    BLUETOOTH_LE,
    REMOTE_NOTIFICATION,
    RECORD_AUDIO,
    BLUETOOTH_SCAN,
    BLUETOOTH_ADVERTISE,
    BLUETOOTH_CONNECT,
    CONTACTS,
    MOTION,
}

enum class PermissionState {
    NotDetermined,

    /**
     * Android-only. This could mean [NotDetermined] or [DeniedAlways], but the OS doesn't
     * expose which of the two it is in all scenarios.
     */
    NotGranted,

    Granted,

    /**
     * Android-only.
     */
    Denied,

    /**
     * On Android only applicable to Push Notifications.
     */
    DeniedAlways,
}

fun PermissionState.canRequest(): Boolean = when (this) {
    PermissionState.Granted -> false
    PermissionState.DeniedAlways -> false
    else -> true
}
