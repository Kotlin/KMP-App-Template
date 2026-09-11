package com.jetbrains.kmpapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.jetbrains.kmpapp.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()

    ComposeViewport(viewportContainerId = "composeTarget") {
        App()
    }
}
