package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
