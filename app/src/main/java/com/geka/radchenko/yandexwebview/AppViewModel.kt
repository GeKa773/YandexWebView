package com.geka.radchenko.yandexwebview

import androidx.lifecycle.ViewModel
import com.geka.radchenko.yandexwebview.pref.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel
@Inject constructor(private val preferences: PreferencesManager) : ViewModel() {

    fun saveLastUrl(url: String) {
        preferences.lastUrl = url
    }

    fun getLastUrl(): String = preferences.lastUrl.ifEmpty { "https://yandex.ru/" }
}