package com.muhrifqii.inject

import com.muhrifqii.injects.ApplicationScope
import me.tatarka.inject.annotations.Component

@ApplicationScope
@Component
abstract class IosApplicationComponent: FeaturedApplicationComponent, DefaultApplicationComponent {
    companion object
}
