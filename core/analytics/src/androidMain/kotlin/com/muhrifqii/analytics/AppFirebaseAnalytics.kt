package com.muhrifqii.analytics

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import me.tatarka.inject.annotations.Inject

@SuppressLint("MissingPermission")
@Inject
class AppFirebaseAnalytics(
    private val context: Application,
) : Analytics {
    private val firebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(context)
    }

    override fun trackScreenView(name: String, arguments: Map<String, *>?) {
        try {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, name)
                arguments?.let {
                    for (entry in arguments) {
                        putString("screen_arg_${entry.key}", "${entry.value}")
                    }
                }
            }
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        } catch (ignored: Throwable) {
        }
    }

    override fun setEnabled(enabled: Boolean) {
        firebaseAnalytics.setAnalyticsCollectionEnabled(enabled)
    }
}
