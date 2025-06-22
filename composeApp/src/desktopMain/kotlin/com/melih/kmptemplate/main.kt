package com.melih.kmptemplate

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.melih.kmptemplate.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMPTemplate",
    ) {
        initKoin()
        App()
    }
}
