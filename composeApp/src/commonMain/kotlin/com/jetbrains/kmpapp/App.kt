package com.jetbrains.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.screens.list.ListScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(ListScreen)
    }
}
