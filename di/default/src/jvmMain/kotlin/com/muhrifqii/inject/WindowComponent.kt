package com.muhrifqii.inject

import com.muhrifqii.injects.ActivityScope
import me.tatarka.inject.annotations.Component

@ActivityScope
@Component
abstract class WindowComponent(
    @Component val applicationComponent: DesktopApplicationComponent,
) : DefaultUiComponent {
    companion object
}
