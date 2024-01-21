package com.nameisjayant.demo.utils

import java.util.prefs.Preferences

object PreferenceStore {
    lateinit var preferences: Preferences
    fun setPreference(preferences: Preferences) {
        this.preferences = preferences
    }

    val index = "index"

}