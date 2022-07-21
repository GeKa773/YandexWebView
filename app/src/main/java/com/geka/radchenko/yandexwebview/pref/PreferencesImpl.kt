package com.geka.radchenko.yandexwebview.pref

import android.content.Context
import android.content.SharedPreferences
import java.util.prefs.Preferences

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesImpl @Inject constructor(private val context: Context) : PreferencesManager {
    companion object {
        private const val NAME = "sPref"
        private const val LAST_URL = "last_url"
    }

    private val prefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    override var lastUrl: String
        get() = prefs.getString(LAST_URL, "") ?: ""
        set(value) {
            prefs.addString(LAST_URL, value)
        }

}

fun SharedPreferences.addString(key: String, value: String) {
    val editor = this.edit()
    editor.putString(key, value)
    editor.apply()
}