package com.melih.kmptemplate

import android.app.Application
import com.melih.kmptemplate.di.initKoin

class KMPTemplateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
