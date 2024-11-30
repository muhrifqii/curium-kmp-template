package com.muhrifqii.notifications

import android.app.Application
import androidx.datastore.dataStoreFile
import com.muhrifqii.data.entities.Notification
import com.muhrifqii.data.protobuf.PendingNotification
import com.muhrifqii.injects.ApplicationScope
import com.muhrifqii.utils.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.toKotlinInstant
import me.tatarka.inject.annotations.Inject
import okio.Path.Companion.toOkioPath
import kotlin.time.Duration.Companion.days

@Inject
@ApplicationScope
class PendingNotificationStore(
    application: Application,
    dispatchers: AppCoroutineDispatchers,
) {
    private val scope = CoroutineScope(dispatchers.io + SupervisorJob())

    private val store by lazy {
        pendingNotificationStore(scope) {
            application.dataStoreFile("pending_notifications.pb")
                .absoluteFile
                .toOkioPath()
        }
    }

    suspend fun findWithId(id: String): Notification? {
        return store.data.firstOrNull()
            ?.notifications
            ?.firstOrNull { it.id == id }
            ?.toNotification()
    }

    suspend fun add(pending: PendingNotification) {
        store.updateData {
            it.copy(
                notifications = (it.notifications + pending)
                    .asReversed()
                    .distinctBy(PendingNotification::id)
            )
        }
    }

    suspend fun removeById(id: String) {
        store.updateData { data ->
            data.copy(data.notifications.filterNot { it.id == id })
        }
    }

    suspend fun getPendingNotifications(): List<Notification> {
        store.updateData { data ->
            val filtered = data.notifications.filter {
                val date = it.date?.toKotlinInstant()
                date != null && date > (Clock.System.now() - 1.days)
            }
            data.copy(filtered)
        }
        return store.data.firstOrNull()
            ?.notifications
            ?.map(PendingNotification::toNotification)
            ?: emptyList()
    }
}

interface PendingNotificationsStoreProvider {
    val pendingNotificationsStore: PendingNotificationStore
}
