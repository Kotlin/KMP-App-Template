package com.example.composekmpapp.shared.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define your color palette
private val LightColorPalette = lightColors(
    primary = Color(0xFF6200EE), // Purple 500
    primaryVariant = Color(0xFF3700B3), // Purple 700
    secondary = Color(0xFF03DAC6), // Teal 200
    secondaryVariant = Color(0xFF018786), // Teal 700
    background = Color.White,
    surface = Color.White,
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

// You can also define a DarkColorPalette if needed

@Composable
fun AppTheme(
    // darkTheme: Boolean = isSystemInDarkTheme(), // If you want to support dark theme
    content: @Composable () -> Unit
) {
    // val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val colors = LightColorPalette // Using light theme for now

    MaterialTheme(
        colors = colors,
        typography = Typography(), // Define your Typography later
        shapes = Shapes(),       // Define your Shapes later
        content = content
    )
}
