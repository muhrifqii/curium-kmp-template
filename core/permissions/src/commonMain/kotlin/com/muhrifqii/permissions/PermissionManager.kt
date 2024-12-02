package com.muhrifqii.permissions

interface PermissionManager {
    fun openAppSettings()

    suspend fun requestPermission(permission: Permission): PermissionState

    suspend fun isPermissionGranted(permission: Permission): Boolean

    suspend fun getPermissionState(permission: Permission): PermissionState
}

private const val MAX_RETRIES = 3

suspend fun PermissionManager.requestPermissionWithRetry(permission: Permission): PermissionState {
    var latestState = getPermissionState(permission)
    for (i in 1..MAX_RETRIES) {
        if (latestState.canRequest()) {
            latestState = requestPermission(permission)
        } else {
            break
        }
    }
    return latestState
}

suspend fun PermissionManager.performAction(
    permission: Permission,
    action: suspend (PermissionState) -> Unit,
) {
    val state = getPermissionState(permission)
    when (state) {
        PermissionState.Granted -> action(state)
        else -> action(requestPermissionWithRetry(permission))
    }
}

internal object EmptyPermissionManager : PermissionManager {
    override fun openAppSettings() = Unit

    override suspend fun requestPermission(permission: Permission): PermissionState =
        getPermissionState(permission)

    override suspend fun isPermissionGranted(permission: Permission): Boolean = false

    override suspend fun getPermissionState(permission: Permission): PermissionState =
        PermissionState.NotDetermined
}
