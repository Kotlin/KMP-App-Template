package com.example.composekmpapp.shared.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composekmpapp.shared.components.AppButton
import com.example.composekmpapp.shared.components.AppTextField
import com.example.composekmpapp.shared.screens.setup.ShiftLocationSelectionScreen
import com.example.composekmpapp.shared.session.SessionManager // Import SessionManager

object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        // In a real app, you'd use a ViewModel or ScreenModel for this logic
        // For simplicity now, keeping state and logic within the Composable

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Login") })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome Back!", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(24.dp))

                AppTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Username",
                    placeholder = "Enter your username"
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    placeholder = "Enter your password",
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppButton(
                    text = "Login",
                    onClick = {
                        isLoading = true
                        // Simulate network call or validation
                        if (username.isNotBlank() && password.isNotBlank()) {
                            // Simulate successful login
                            // In a real app, you'd verify credentials
                            // For now, just navigate
                            // Roles (Admin/User) would be determined here or by backend
                            // and stored, possibly in a session manager or app state.
                            println("Login successful for user: $username")
                            // TODO: Navigate to ShiftLocationSelectionScreen
                            // For now, navigating to a generic placeholder
                            SessionManager.currentUserName = username // Store username
                            println("Login successful for user: $username. Stored in SessionManager.")
                            navigator.replace(ShiftLocationSelectionScreen) // Navigate to the correct screen
                        } else {
                            // Handle error - show a message, etc.
                            println("Login failed: Username or password cannot be blank.")
                            isLoading = false
                        }
                        // Simulate delay
                        // kotlinx.coroutines.GlobalScope.launch {
                        //    kotlinx.coroutines.delay(1000)
                        //    isLoading = false
                        // }
                    },
                    isLoading = isLoading,
                    enabled = username.isNotBlank() && password.isNotBlank() && !isLoading
                )
            }
        }
    }
}
