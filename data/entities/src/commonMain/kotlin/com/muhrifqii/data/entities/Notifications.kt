package com.muhrifqii.data.entities

import kotlinx.datetime.Instant

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val channel: NotificationChannel,
    val date: Instant,
    val deeplinkUrl: String? = null,
)

enum class NotificationChannel(val id: String) {
    APPLICATION("application"),
    PROMOTIONAL("promotional"),
    DEV("dev-testing"),
    ;

    override fun toString(): String {
        return id
    }

    companion object {
        fun fromId(id: String): NotificationChannel {
            return entries.first { it.id == id }
        }
    }
}
