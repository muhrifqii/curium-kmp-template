package com.muhrifqii.notifications

import com.muhrifqii.data.entities.Notification
import com.muhrifqii.data.entities.NotificationChannel

interface NotificationManager {

    suspend fun schedule(notification: Notification) = Unit

    suspend fun cancel(notification: Notification) = Unit

    suspend fun cancelAll(notifications: List<Notification>) {
        notifications.forEach { cancel(it) }
    }

    suspend fun cancelAllInChannel(channel: NotificationChannel) {
        cancelAll(
            getPendingNotifications().filter { it.channel == channel },
        )
    }

    suspend fun getPendingNotifications(): List<Notification> = emptyList()
}

internal object EmptyNotificationManager : NotificationManager
