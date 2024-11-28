package com.muhrifqii.inject

import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Component

@ApplicationScope
@Component
abstract class DesktopApplicationComponent : FeaturedApplicationComponent,
    DefaultApplicationComponent {
    companion object
}
