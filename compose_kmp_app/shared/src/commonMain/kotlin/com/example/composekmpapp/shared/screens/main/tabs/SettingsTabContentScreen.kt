package com.example.composekmpapp.shared.screens.main.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
 // Assuming we have stored these somewhere globally or can access them.
// For this example, we'll use placeholder/hardcoded values.
// In a real app, this would come from a UserRepository or SessionManager.
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composekmpapp.shared.components.AppButton
import com.example.composekmpapp.shared.screens.auth.LoginScreen
import com.example.composekmpapp.shared.session.SessionManager // Import SessionManager

object SettingsTabContentScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val userName = SessionManager.currentUserName ?: "N/A"
        val shiftName = SessionManager.currentShift?.name ?: "N/A"
        val locationName = SessionManager.currentLocation?.name ?: "N/A"
        val positionName = SessionManager.currentUserPosition ?: "N/A"

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("User Profile", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            ProfileInfoRow("Name:", userName)
            ProfileInfoRow("Current Shift:", shiftName)
            ProfileInfoRow("Current Location:", locationName)
            ProfileInfoRow("Position:", positionName)

            Spacer(modifier = Modifier.weight(1f)) // Pushes logout button to the bottom

            AppButton(
                text = "Logout",
                onClick = {
                    println("User logging out...")
                    SessionManager.clearSession() // Clear the session data
                    navigator.replaceAll(LoginScreen) // Navigate back to LoginScreen
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Text(value, fontSize = 16.sp)
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}
