package com.example.composekmpapp.shared

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.composekmpapp.shared.screens.auth.LoginScreen // Updated import
import com.example.composekmpapp.shared.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigator(LoginScreen) { navigator -> // Start with LoginScreen
            SlideTransition(navigator)
        }
    }
}
