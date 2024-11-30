package com.muhrifqii.notifications

import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Provides

actual interface NotificationPlatformComponent {
    @Provides
    @ApplicationScope
    fun provideNotificationManager(): NotificationManager = EmptyNotificationManager
}
