package com.muhrifqii.notifications

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioSerializer
import androidx.datastore.core.okio.OkioStorage
import com.muhrifqii.data.entities.Notification
import com.muhrifqii.data.entities.NotificationChannel
import com.muhrifqii.data.protobuf.PendingNotification
import com.muhrifqii.data.protobuf.PendingNotifications
import kotlinx.coroutines.CoroutineScope
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toKotlinInstant
import okio.BufferedSink
import okio.BufferedSource
import okio.FileSystem
import okio.IOException
import okio.Path

internal fun pendingNotificationStore(
    coroutineScope: CoroutineScope,
    path: () -> Path
): DataStore<PendingNotifications> = DataStoreFactory.create(
    storage = OkioStorage(
        fileSystem = FileSystem.SYSTEM,
        serializer = object : OkioSerializer<PendingNotifications> {
            override val defaultValue: PendingNotifications
                get() = PendingNotifications()


            override suspend fun readFrom(source: BufferedSource): PendingNotifications {
                return try {
                    PendingNotifications.ADAPTER.decode(source)
                } catch (err: IOException) {
                    throw CorruptionException("Cannot read proto.", err)
                }
            }

            override suspend fun writeTo(t: PendingNotifications, sink: BufferedSink) {
                PendingNotifications.ADAPTER.encode(sink, t)
            }
        },
        producePath = path,
    ),
    corruptionHandler = null,
    scope = coroutineScope,
)

internal fun PendingNotification.toNotification(): Notification =
    Notification(
        id = id,
        title = title,
        message = message,
        channel = NotificationChannel.fromId(channel_id),
        date = date?.toKotlinInstant() ?: Instant.DISTANT_PAST,
        deeplinkUrl = deeplink_uri,
    )

internal fun Notification.toPendingNotification(): PendingNotification {
    return PendingNotification(
        id = id,
        title = title,
        message = message,
        deeplink_uri = deeplinkUrl,
        date = date.toJavaInstant(),
        channel_id = channel.id,
    )
}

internal fun Notification.buildPendingIntent(
    context: Context,
): PendingIntent {
    val intent = PostNotificationBroadcastReceiver.buildIntent(context, id)
    return PendingIntent.getBroadcast(context, id.hashCode(), intent, PENDING_INTENT_FLAGS)
}

private const val PENDING_INTENT_FLAGS = FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT or FLAG_ONE_SHOT

/**
 * It Requires Application to implement PendingNotificationStoreProvider
 */
val Context.pendingNotificationsStore: PendingNotificationStore
    get() = (applicationContext as PendingNotificationsStoreProvider).pendingNotificationsStore

