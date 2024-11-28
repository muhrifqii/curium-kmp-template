package com.muhrifqii.inject

import android.app.Activity
import androidx.core.os.ConfigurationCompat
import me.tatarka.inject.annotations.Provides
import java.util.Locale

interface FeaturedActivityComponent {
    @get:Provides
    val activity: Activity

    @Provides
    fun provideActivityLocale(activity: Activity): Locale =
        ConfigurationCompat.getLocales(activity.resources.configuration)
            .get(0) ?: Locale.getDefault()
}
