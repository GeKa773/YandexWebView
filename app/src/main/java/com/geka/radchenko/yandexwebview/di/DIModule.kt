package com.geka.radchenko.yandexwebview.di

import android.app.Application
import com.geka.radchenko.yandexwebview.pref.PreferencesImpl
import com.geka.radchenko.yandexwebview.pref.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    @Provides
    @Singleton
    fun providePrefs(application: Application): PreferencesManager {
        return PreferencesImpl(application)
    }
}