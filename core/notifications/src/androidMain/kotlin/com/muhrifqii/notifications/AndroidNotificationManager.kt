package com.muhrifqii.notifications

import android.app.AlarmManager
import android.app.Application
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_DEFAULT
import androidx.core.content.getSystemService
import com.muhrifqii.data.entities.Notification
import com.muhrifqii.data.entities.NotificationChannel
import com.muhrifqii.logger.Logger
import kotlinx.datetime.Clock
import me.tatarka.inject.annotations.Inject
import kotlin.time.Duration.Companion.minutes

@Inject
class AndroidNotificationManager(
    private val application: Application,
    private val store: PendingNotificationStore,
    private val log: Logger,
) : NotificationManager {

    private val notificationManager by lazy { NotificationManagerCompat.from(application) }
    private val alarmManager by lazy { application.getSystemService<AlarmManager>()!! }

    override suspend fun schedule(notification: Notification) {
        notificationManager.createChannel(notification.channel)
        store.add(notification.toPendingNotification())

        // decide whether to send the broadcast now, or set as an alarm
        val windowStartTime = notification.date - ALARM_WINDOW_LENGTH
        if (windowStartTime <= Clock.System.now()) {
            // if the window start time is in the past, send the broadcast now
            log.d(tag = this::class.simpleName) {
                "Sending notification now: $notification"
            }
            application.sendBroadcast(
                PostNotificationBroadcastReceiver.buildIntent(
                    application,
                    notification.id
                )
            )
        } else {
            log.d { "Scheduling notification for $windowStartTime: $notification" }

            alarmManager.setWindow(
                AlarmManager.RTC_WAKEUP,
                windowStartTime.toEpochMilliseconds(),
                ALARM_WINDOW_LENGTH.inWholeMilliseconds,
                notification.buildPendingIntent(application)
            )
        }
    }

    override suspend fun cancel(notification: Notification) {
        alarmManager.cancel(notification.buildPendingIntent(application))
        store.removeById(notification.id)
    }

    override suspend fun getPendingNotifications(): List<Notification> {
        return store.getPendingNotifications()
    }

    private companion object {
        // We request 10 mins as Android S can choose to apply a minimum of 10 mins anyway
        // Being earlier is better than being late
        val ALARM_WINDOW_LENGTH = 10.minutes
    }
}

private fun NotificationManagerCompat.createChannel(channel: NotificationChannel) {
    val androidChannel = NotificationChannelCompat.Builder(channel.id, IMPORTANCE_DEFAULT)
        .apply {
            // todo: use Res.string
            when (channel) {
                NotificationChannel.APPLICATION -> {
                    setName("Main Notifications")
                    setDescription("All purpose main notification channel")
                    setVibrationEnabled(true)
                }

                NotificationChannel.PROMOTIONAL -> {
                    setName("Promotional")
                    setDescription("Promotional notifications")
                    setVibrationEnabled(true)
                }

                NotificationChannel.DEV -> {
                    setName("Developer testing")
                    setVibrationEnabled(true)
                }
            }
        }
        .build()
    createNotificationChannel(androidChannel)
}
