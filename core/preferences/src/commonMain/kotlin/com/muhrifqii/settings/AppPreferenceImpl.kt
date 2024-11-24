package com.muhrifqii.settings

import com.muhrifqii.utils.AppCoroutineDispatchers
import com.muhrifqii.injects.ApplicationCoroutineScope
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject
import kotlin.time.Duration.Companion.seconds

@Inject
@OptIn(ExperimentalSettingsApi::class)
class AppPreferenceImpl(
    settings: Lazy<ObservableSettings>,
    private val coroutineScope: ApplicationCoroutineScope,
    private val dispatchers: AppCoroutineDispatchers,
) : AppPreferences {
    private val settings: ObservableSettings by settings
    private val flowSettings by lazy { settings.value.toFlowSettings(dispatchers.io) }

    override val theme: Preference<Theme> by lazy {
        MappingPreference(
            KEY_THEME,
            Theme.SYSTEM,
            Theme::fromValue,
            Theme::value
        )
    }

    override val useDynamicColor: Preference<Boolean> by lazy {
        BooleanPreference(KEY_USE_DYNAMIC_COLORS, false)
    }

    override val reportAnalytics: Preference<Boolean> by lazy {
        BooleanPreference(KEY_OPT_IN_ANALYTICS_REPORTING, true)
    }

    override val notificationEnabled: Preference<Boolean> by lazy {
        BooleanPreference(KEY_NOTIFICATIONS, true)
    }

    private inner class MappingPreference<V>(
        private val key: String,
        override val defaultValue: V,
        private val toValue: (String) -> V,
        private val fromValue: (V) -> String,
    ) : Preference<V> {
        override suspend fun set(value: V) = withContext(dispatchers.io) {
            settings[key] = fromValue(value)
        }

        override suspend fun get(): V = withContext(dispatchers.io) {
            settings.getStringOrNull(key)?.let(toValue) ?: defaultValue
        }

        override val flow: Flow<V> by lazy {
            flowSettings.getStringOrNullFlow(key)
                .map { it?.let(toValue) ?: defaultValue }
                .shareIn(
                    scope = coroutineScope,
                    started = SharingStarted.WhileSubscribed(SUBSCRIBED_TIMEOUT),
                )
        }
    }

    private inner class BooleanPreference(
        private val key: String,
        override val defaultValue: Boolean = false,
    ) : Preference<Boolean> {
        override suspend fun set(value: Boolean) = withContext(dispatchers.io) {
            settings[key] = value
        }

        override suspend fun get(): Boolean = withContext(dispatchers.io) {
            settings.getBoolean(key, defaultValue)
        }

        override val flow: StateFlow<Boolean> by lazy {
            flowSettings
                .getBooleanFlow(key, defaultValue)
                .stateIn(
                    scope = coroutineScope,
                    started = SharingStarted.WhileSubscribed(SUBSCRIBED_TIMEOUT),
                    initialValue = defaultValue,
                )
        }
    }

    private companion object {
        val SUBSCRIBED_TIMEOUT = 20.seconds
    }
}
