package com.muhrifqii.permissions

import com.muhrifqii.injects.ApplicationScope
import com.muhrifqii.logger.Logger
import dev.icerock.moko.permissions.ios.PermissionsController
import me.tatarka.inject.annotations.Provides

actual interface PermissionPlatformComponent {
    @Provides
    @ApplicationScope
    fun providePermissionManager(logger: Logger): PermissionManager =
        MokoPermissionManager(PermissionsController(), logger)
}
