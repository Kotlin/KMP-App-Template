package com.jetbrains.kmpapp.screens

import androidx.compose.ui.graphics.Color

fun placeholderColor(objectId: Int): Color {
    val hue = (objectId * 137).mod(360)
    return Color.hsl(hue.toFloat(), saturation = 0.5f, lightness = 0.75f)
}
