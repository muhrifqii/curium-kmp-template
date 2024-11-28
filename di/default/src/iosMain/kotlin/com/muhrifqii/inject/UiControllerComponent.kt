package com.muhrifqii.inject

import com.muhrifqii.injects.ActivityScope
import me.tatarka.inject.annotations.Component

@ActivityScope
@Component
abstract class UiControllerComponent(
    @Component val applicationComponent: IosApplicationComponent,
) : DefaultUiComponent {
    companion object
}
