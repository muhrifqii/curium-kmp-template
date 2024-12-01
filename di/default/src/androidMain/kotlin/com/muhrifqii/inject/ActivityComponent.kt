package com.muhrifqii.inject

import android.app.Activity
import com.muhrifqii.injects.ActivityScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@ActivityScope
@Component
abstract class ActivityComponent(
    @get:Provides override val activity: Activity,
    @Component val applicationComponent: AndroidApplicationComponent,
) : FeaturedActivityComponent, DefaultUiComponent {
    companion object
}
