package com.muhrifqii.permissions

import me.tatarka.inject.annotations.Provides

actual interface PermissionPlatformComponent {
    @Provides
    fun providePermissionManager(): PermissionManager = EmptyPermissionManager
}
