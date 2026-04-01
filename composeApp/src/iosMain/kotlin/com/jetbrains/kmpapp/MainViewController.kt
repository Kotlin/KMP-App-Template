package com.jetbrains.kmpapp

import androidx.compose.ui.window.ComposeUIViewController
import com.jetbrains.kmpapp.di.KoinApp
import org.koin.core.Koin
import org.koin.plugin.module.dsl.startKoin

private val koin: Koin = startKoin<KoinApp>().koin

fun MainViewController() = ComposeUIViewController { App() }
