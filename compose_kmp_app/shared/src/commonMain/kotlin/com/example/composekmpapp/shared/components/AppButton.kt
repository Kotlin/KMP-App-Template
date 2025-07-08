package com.example.composekmpapp.shared.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(vertical = 4.dp),
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary, // Define in MaterialTheme
            contentColor = Color.White,
            disabledBackgroundColor = Color.LightGray
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.onPrimary, // Define in MaterialTheme
                strokeWidth = 2.dp
            )
        } else {
            Text(text)
        }
    }
}
