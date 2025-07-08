package com.example.composekmpapp.shared.screens.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composekmpapp.shared.components.AppButton
import com.example.composekmpapp.shared.data.Location
import com.example.composekmpapp.shared.data.SampleData
import com.example.composekmpapp.shared.data.Shift
import com.example.composekmpapp.shared.screens.main.MainAppScreen
import com.example.composekmpapp.shared.session.SessionManager // Import SessionManager

object ShiftLocationSelectionScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var selectedShift by remember { mutableStateOf<Shift?>(null) }
        var selectedLocation by remember { mutableStateOf<Location?>(null) }

        var shiftDropdownExpanded by remember { mutableStateOf(false) }
        var locationDropdownExpanded by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Setup Your Session") })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Select Shift", style = MaterialTheme.typography.h6)
                Box {
                    OutlinedTextField(
                        value = selectedShift?.name ?: "Select a Shift",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth().clickable { shiftDropdownExpanded = true },
                        trailingIcon = { Icon(Icons.Filled.ArrowDropDown, "Select Shift") }
                    )
                    DropdownMenu(
                        expanded = shiftDropdownExpanded,
                        onDismissRequest = { shiftDropdownExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SampleData.shifts.forEach { shift ->
                            DropdownMenuItem(onClick = {
                                selectedShift = shift
                                shiftDropdownExpanded = false
                            }) {
                                Text(shift.name)
                            }
                        }
                    }
                }

                Text("Select Location", style = MaterialTheme.typography.h6)
                Box {
                    OutlinedTextField(
                        value = selectedLocation?.name ?: "Select a Location",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth().clickable { locationDropdownExpanded = true },
                        trailingIcon = { Icon(Icons.Filled.ArrowDropDown, "Select Location") }
                    )
                    DropdownMenu(
                        expanded = locationDropdownExpanded,
                        onDismissRequest = { locationDropdownExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SampleData.locations.forEach { location ->
                            DropdownMenuItem(onClick = {
                                selectedLocation = location
                                locationDropdownExpanded = false
                            }) {
                                Text(location.name)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                AppButton(
                    text = "Confirm and Proceed",
                    onClick = {
                        if (selectedShift != null && selectedLocation != null) {
                            // Store in SessionManager
                            SessionManager.currentShift = selectedShift
                            SessionManager.currentLocation = selectedLocation
                            // We can use the username already set or a default if needed
                            // SessionManager.currentUserName is likely already set from LoginScreen
                            // SessionManager.currentUserPosition could be set here or based on login response
                            println("Selected Shift: ${selectedShift!!.name}, Location: ${selectedLocation!!.name}. Stored in SessionManager.")
                            navigator.replaceAll(MainAppScreen) // Navigate to the actual MainAppScreen
                        } else {
                            // Optionally, show a message to select both
                            println("Please select both shift and location.")
                        }
                    },
                    enabled = selectedShift != null && selectedLocation != null
                )
            }
        }
    }
}
