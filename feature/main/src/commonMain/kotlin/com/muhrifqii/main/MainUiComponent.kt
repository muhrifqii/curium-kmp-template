package com.muhrifqii.main

import com.muhrifqii.injects.ActivityScope
import me.tatarka.inject.annotations.Provides

interface MainUiComponent {
    @Provides
    @ActivityScope
    fun bindMainContent(impl: DefaultMainContent): MainContent = impl
}
