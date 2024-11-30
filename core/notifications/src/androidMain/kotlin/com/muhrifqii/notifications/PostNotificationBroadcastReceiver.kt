package com.muhrifqii.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.muhrifqii.logger.NapierLogger
import kotlinx.coroutines.runBlocking

class PostNotificationBroadcastReceiver : BroadcastReceiver() {
    private val log = NapierLogger

    override fun onReceive(context: Context, intent: Intent) {
        log.d(tag = TAG) { "Received intent: $intent" }

        val id = intent.getStringExtra(EXTRA_ID)
        if (id == null) {
            log.d(tag = TAG) { "No notification id found in intent" }
            return
        }

        val notificationManager = NotificationManagerCompat.from(context)
        val store = context.pendingNotificationsStore
        val result = goAsync()

        runBlocking {
            val pending = store.findWithId(id) ?: run {
                log.d(tag = TAG) { "No pending notification found with id: $id" }
                result.finish()
                return@runBlocking
            }
            log.d(tag = TAG) { "Found pending notification $id: $pending" }
            val notification = NotificationCompat.Builder(context, pending.channel.id)
                .setContentTitle(pending.title)
                .setContentText(pending.message)
                .apply {
                    if (pending.deeplinkUrl != null) {
                        setContentIntent(
                            PendingIntent.getActivity(
                                context,
                                0,
                                Intent(Intent.ACTION_VIEW, pending.deeplinkUrl!!.toUri()).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                },
                                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                            )
                        )
                    }
                }
                .build()

            try {
                notificationManager.notify(id, 0, notification)
            } catch (err: SecurityException) {
                log.e(err, tag = TAG) { "Failed to send notification" }
            } finally {
                store.removeById(id)
                result.finish()
            }
        }
    }

    companion object {
        private val TAG = PostNotificationBroadcastReceiver::class.simpleName
        private const val EXTRA_ID = "notification_id"

        fun buildIntent(context: Context, id: String): Intent {
            return Intent(context, PostNotificationBroadcastReceiver::class.java)
                .putExtra(EXTRA_ID, id)
        }
    }
}
