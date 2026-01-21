package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.di.KoinApp
import org.koin.plugin.module.dsl.startKoin

class MuseumApp : Application() {
    val koin by lazy {
        startKoin<KoinApp>().koin
    }

    override fun onCreate() {
        super.onCreate()
        koin.get<MuseumRepository>().initialize()
    }
}
