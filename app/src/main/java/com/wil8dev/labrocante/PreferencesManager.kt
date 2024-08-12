package com.wil8dev.labrocante

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext
    context: Context,
) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        "app_preferences",
        Context.MODE_PRIVATE,
    )
    var isFirstLaunch: Boolean
        get() = preferences.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) = preferences.edit { putBoolean(KEY_FIRST_LAUNCH, value) }

    companion object {

        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
}
