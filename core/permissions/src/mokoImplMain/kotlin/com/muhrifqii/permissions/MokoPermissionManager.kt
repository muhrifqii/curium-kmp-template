package com.muhrifqii.permissions

import com.muhrifqii.logger.Logger
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.Permission as MokoPermission
import dev.icerock.moko.permissions.PermissionState as MokoPermissionState

internal class MokoPermissionManager(
    val controller: PermissionsController,
    val log: Logger,
) : PermissionManager {
    override fun openAppSettings() {
        controller.openAppSettings()
    }

    override suspend fun requestPermission(permission: Permission): PermissionState {
        try {
            controller.providePermission(permission.toMoko())
        } catch (e: Exception) {
            log.i(e, TAG) {
                "Exception thrown during permission request $permission"
            }
        }
        return getPermissionState(permission)
    }

    override suspend fun isPermissionGranted(permission: Permission): Boolean =
        controller.isPermissionGranted(permission.toMoko())

    override suspend fun getPermissionState(permission: Permission): PermissionState =
        controller.getPermissionState(permission.toMoko())
            .fromMoko()

    companion object {
        val TAG = this::class.simpleName
    }
}

internal fun Permission.toMoko(): MokoPermission = when (this) {
    Permission.CAMERA -> MokoPermission.CAMERA
    Permission.GALLERY -> MokoPermission.GALLERY
    Permission.STORAGE -> MokoPermission.STORAGE
    Permission.WRITE_STORAGE -> MokoPermission.WRITE_STORAGE
    Permission.LOCATION -> MokoPermission.LOCATION
    Permission.COARSE_LOCATION -> MokoPermission.COARSE_LOCATION
    Permission.BACKGROUND_LOCATION -> MokoPermission.BACKGROUND_LOCATION
    Permission.BLUETOOTH_LE -> MokoPermission.BLUETOOTH_LE
    Permission.REMOTE_NOTIFICATION -> MokoPermission.REMOTE_NOTIFICATION
    Permission.RECORD_AUDIO -> MokoPermission.RECORD_AUDIO
    Permission.BLUETOOTH_SCAN -> MokoPermission.BLUETOOTH_SCAN
    Permission.BLUETOOTH_ADVERTISE -> MokoPermission.BLUETOOTH_ADVERTISE
    Permission.BLUETOOTH_CONNECT -> MokoPermission.BLUETOOTH_CONNECT
    Permission.CONTACTS -> MokoPermission.CONTACTS
    Permission.MOTION -> MokoPermission.MOTION
}

internal fun MokoPermissionState.fromMoko(): PermissionState = when (this) {
    MokoPermissionState.NotDetermined -> PermissionState.NotDetermined
    MokoPermissionState.NotGranted -> PermissionState.NotGranted
    MokoPermissionState.Granted -> PermissionState.Granted
    MokoPermissionState.Denied -> PermissionState.Denied
    MokoPermissionState.DeniedAlways -> PermissionState.DeniedAlways
}
