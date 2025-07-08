package com.example.composekmpapp.shared

import androidx.compose.ui.window.ComposeUIViewController

// This function will be called from iOS to bootstrap the Compose UI
fun MainViewController() = ComposeUIViewController { App() }
