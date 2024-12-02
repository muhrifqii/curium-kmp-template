package com.muhrifqii.inject

import com.muhrifqii.analytics.AnalyticsComponent
import com.muhrifqii.injects.ApplicationCoroutineScope
import com.muhrifqii.injects.ApplicationScope
import com.muhrifqii.logger.LoggerComponent
import com.muhrifqii.notifications.NotificationComponent
import com.muhrifqii.permissions.PermissionComponent
import com.muhrifqii.settings.PreferencesComponent
import com.muhrifqii.utils.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import me.tatarka.inject.annotations.Provides

interface FeaturedApplicationComponent :
    FeaturedPlatformApplicationComponent,
    AnalyticsComponent,
    LoggerComponent,
    NotificationComponent,
    PermissionComponent,
    PreferencesComponent {

    val initializers: AppInitializerSet
    val dispatchers: AppCoroutineDispatchers

    @ApplicationScope
    @Provides
    fun provideCoroutineDispatchers(): AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        computation = Dispatchers.Default,
        databaseRead = Dispatchers.IO.limitedParallelism(4),
        databaseWrite = Dispatchers.IO.limitedParallelism(1)
    )

    @ApplicationScope
    @Provides
    fun provideApplicationCoroutineScope(
        dispatchers: AppCoroutineDispatchers
    ): ApplicationCoroutineScope =
        CoroutineScope(dispatchers.main + SupervisorJob())
}
