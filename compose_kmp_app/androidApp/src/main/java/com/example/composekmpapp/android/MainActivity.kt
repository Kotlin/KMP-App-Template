package com.example.composekmpapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composekmpapp.shared.App // Assuming App composable will be in shared

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App() // This will be our shared root Composable
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}
