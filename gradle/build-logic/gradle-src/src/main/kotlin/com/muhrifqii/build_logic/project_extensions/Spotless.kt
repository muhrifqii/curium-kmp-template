package com.muhrifqii.build_logic.project_extensions

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.LineEnding
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureSpotless() {

    with(pluginManager) {
        apply("com.diffplug.spotless")
    }

    spotless {
        // Workaround for https://github.com/diffplug/spotless/issues/1644
        lineEndings = LineEnding.PLATFORM_NATIVE

        val ktlintVersion = libs.findVersion("ktlint").get().requiredVersion

        kotlin {
            target("src/**/*.kt")
            ktlint(ktlintVersion)
        }

        kotlinGradle {
            target("*.kts")
            ktlint(ktlintVersion)
        }
    }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) =
    extensions.configure<SpotlessExtension>(action)
