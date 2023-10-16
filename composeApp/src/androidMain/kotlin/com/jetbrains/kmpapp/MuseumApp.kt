package com.jetbrains.kmpapp

import android.app.Application
import di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
