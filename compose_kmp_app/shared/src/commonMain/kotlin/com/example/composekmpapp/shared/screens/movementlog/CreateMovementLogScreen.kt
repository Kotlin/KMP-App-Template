package com.example.composekmpapp.shared.screens.movementlog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composekmpapp.shared.components.AppButton
import com.example.composekmpapp.shared.components.AppTextField
import com.example.composekmpapp.shared.data.MovementDirection
import com.example.composekmpapp.shared.data.MovementLogEntry
import com.example.composekmpapp.shared.data.PersonType
import com.example.composekmpapp.shared.data.SampleData
import kotlinx.datetime.Clock

object CreateMovementLogScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var personName by remember { mutableStateOf("") }
        var selectedPersonType by remember { mutableStateOf<PersonType?>(null) }
        var personTypeDropdownExpanded by remember { mutableStateOf(false) }
        var selectedDirection by remember { mutableStateOf<MovementDirection?>(null) }
        var directionDropdownExpanded by remember { mutableStateOf(false) }
        var vehicleDetails by remember { mutableStateOf("") }
        var purpose by remember { mutableStateOf("") }
        var remarks by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Create Movement Log") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppTextField(value = personName, onValueChange = { personName = it }, label = "Person/Goods Name")

                // Person Type Dropdown
                Box {
                    OutlinedTextField(
                        value = selectedPersonType?.name?.replace("_", " ")?.lowercase()
                            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                            ?: "Select Person Type",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth().clickable { personTypeDropdownExpanded = true },
                        label = { Text("Person Type") },
                        trailingIcon = { Icon(Icons.Filled.ArrowDropDown, "Select Type") }
                    )
                    DropdownMenu(
                        expanded = personTypeDropdownExpanded,
                        onDismissRequest = { personTypeDropdownExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PersonType.entries.forEach { type ->
                            DropdownMenuItem(onClick = {
                                selectedPersonType = type
                                personTypeDropdownExpanded = false
                            }) {
                                Text(type.name.replace("_", " ").lowercase()
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() })
                            }
                        }
                    }
                }

                // Direction Dropdown
                Box {
                    OutlinedTextField(
                        value = selectedDirection?.name?.lowercase()?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } ?: "Select Direction",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth().clickable { directionDropdownExpanded = true },
                        label = { Text("Direction (Entry/Exit)") },
                        trailingIcon = { Icon(Icons.Filled.ArrowDropDown, "Select Direction") }
                    )
                    DropdownMenu(
                        expanded = directionDropdownExpanded,
                        onDismissRequest = { directionDropdownExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MovementDirection.entries.forEach { direction ->
                            DropdownMenuItem(onClick = {
                                selectedDirection = direction
                                directionDropdownExpanded = false
                            }) {
                                Text(direction.name.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() })
                            }
                        }
                    }
                }

                AppTextField(value = vehicleDetails, onValueChange = { vehicleDetails = it }, label = "Vehicle Details (Optional)")
                AppTextField(value = purpose, onValueChange = { purpose = it }, label = "Purpose of Visit/Movement (Optional)")
                AppTextField(value = remarks, onValueChange = { remarks = it }, label = "Remarks (Optional)", modifier = Modifier.height(100.dp))

                Spacer(modifier = Modifier.weight(1f))

                AppButton(
                    text = "Submit Log",
                    onClick = {
                        if (personName.isNotBlank() && selectedPersonType != null && selectedDirection != null) {
                            val newLog = MovementLogEntry(
                                id = "log_${Clock.System.now().toEpochMilliseconds()}", // Simple unique ID
                                personName = personName,
                                personType = selectedPersonType!!,
                                direction = selectedDirection!!,
                                vehicleDetails = vehicleDetails.takeIf { it.isNotBlank() },
                                purpose = purpose.takeIf { it.isNotBlank() },
                                remarks = remarks.takeIf { it.isNotBlank() }
                            )
                            SampleData.movementLogs.add(0, newLog) // Add to top of the list
                            println("New log created: ${newLog.personName}")
                            navigator.pop()
                        } else {
                            // Show error or highlight fields
                            println("Error: Please fill all required fields.")
                        }
                    },
                    enabled = personName.isNotBlank() && selectedPersonType != null && selectedDirection != null
                )
            }
        }
    }
}
