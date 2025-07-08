package com.example.composekmpapp.shared.screens.main.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composekmpapp.shared.data.MovementLogEntry
import com.example.composekmpapp.shared.data.SampleData
import com.example.composekmpapp.shared.screens.movementlog.CreateMovementLogScreen

object MovementLogTabContentScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        // This state is used to trigger recomposition when logs are added/updated.
        // In a real app with a proper ViewModel and StateFlow, this might not be needed
        // if the LazyColumn items parameter directly observes the flow.
        var logs by remember { mutableStateOf(SampleData.movementLogs.toList()) }

        // This LaunchedEffect will refresh the list when the screen becomes active
        // or when SampleData.movementLogs changes.
        // This is a simple way to ensure updates. A more robust solution would involve
        // a reactive data source (e.g. Flow) from a ViewModel/repository.
        LaunchedEffect(Unit, SampleData.movementLogs.size) {
            logs = SampleData.movementLogs.toList()
        }


        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navigator.push(CreateMovementLogScreen)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Create New Log")
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(8.dp)) {
                if (logs.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No movement logs yet. Tap '+' to create one.")
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(logs, key = { it.id }) { logEntry ->
                            MovementLogItemCard(logEntry)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovementLogItemCard(logEntry: MovementLogEntry) {
    Card(
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    logEntry.personName,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    logEntry.direction.name,
                    color = if (logEntry.direction == com.example.composekmpapp.shared.data.MovementDirection.ENTRY) Color(0xFF28a745) else Color(0xFFdc3545), // Green for entry, Red for exit
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Type: ${logEntry.personType.name.replace("_", " ").lowercase().replaceFirstChar { it.titlecase() }}",
                style = MaterialTheme.typography.body2
            )
            Text(
                "Time: ${logEntry.getFormattedTimestamp()}",
                style = MaterialTheme.typography.caption,
                color = Color.Gray
            )
            logEntry.vehicleDetails?.let {
                Text("Vehicle: $it", style = MaterialTheme.typography.body2)
            }
            logEntry.purpose?.let {
                Text("Purpose: $it", style = MaterialTheme.typography.body2)
            }
            logEntry.remarks?.let {
                Text("Remarks: $it", style = MaterialTheme.typography.body2)
            }
        }
    }
}
