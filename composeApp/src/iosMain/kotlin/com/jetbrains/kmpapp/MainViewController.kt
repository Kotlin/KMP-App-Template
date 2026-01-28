package com.jetbrains.kmpapp

import androidx.compose.ui.window.ComposeUIViewController
import com.jetbrains.kmpapp.di.AppGraph
import dev.zacsweers.metro.createGraph

private val appGraph: AppGraph by lazy {
    createGraph<AppGraph>().also {
        it.museumRepository.initialize()
    }
}

fun MainViewController() = ComposeUIViewController { App(appGraph) }
