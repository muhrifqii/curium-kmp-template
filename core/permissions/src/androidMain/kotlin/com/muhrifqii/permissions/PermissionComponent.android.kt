package com.muhrifqii.permissions

import android.app.Application
import com.muhrifqii.injects.ApplicationScope
import com.muhrifqii.logger.Logger
import dev.icerock.moko.permissions.PermissionsController
import me.tatarka.inject.annotations.Provides

actual interface PermissionPlatformComponent {
    @Provides
    @ApplicationScope
    fun providePermissionManager(application: Application, logger: Logger): PermissionManager =
        MokoPermissionManager(PermissionsController(application), logger)
}
