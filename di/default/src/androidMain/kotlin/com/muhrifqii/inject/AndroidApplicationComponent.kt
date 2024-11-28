package com.muhrifqii.inject

import android.app.Application
import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@ApplicationScope
@Component
abstract class AndroidApplicationComponent(
    @get:Provides val application: Application,
) : FeaturedApplicationComponent, DefaultApplicationComponent {

    companion object
}
