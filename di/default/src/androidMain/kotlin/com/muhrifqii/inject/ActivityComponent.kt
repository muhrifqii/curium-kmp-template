package com.muhrifqii.inject

import android.app.Activity
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

abstract class ActivityComponent(
    @get:Provides override val activity: Activity,
    @Component val applicationComponent: AndroidApplicationComponent,
) : FeaturedActivityComponent, DefaultUiComponent {
    companion object
}
