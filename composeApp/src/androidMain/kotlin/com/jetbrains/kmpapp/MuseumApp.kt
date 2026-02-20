package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.di.AppGraph
import dev.zacsweers.metro.createGraph

class MuseumApp : Application() {
    val appGraph: AppGraph by lazy {
        createGraph<AppGraph>()
    }

    override fun onCreate() {
        super.onCreate()
        appGraph.museumRepository.initialize()
    }
}
