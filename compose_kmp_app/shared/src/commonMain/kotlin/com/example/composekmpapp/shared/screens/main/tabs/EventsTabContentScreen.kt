package com.example.composekmpapp.shared.screens.main.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.composekmpapp.shared.components.AppButton
import com.example.composekmpapp.shared.components.AppTextField
import com.example.composekmpapp.shared.data.AppEvent
import com.example.composekmpapp.shared.data.AppEventType
import com.example.composekmpapp.shared.data.SampleData
import kotlinx.datetime.Clock

object EventsTabContentScreen : Screen {
    @Composable
    override fun Content() {
        var selectedEventType by remember { mutableStateOf<AppEventType?>(null) }
        var eventTypeDropdownExpanded by remember { mutableStateOf(false) }
        var notes by remember { mutableStateOf("") }
        var eventSubmissionMessage by remember { mutableStateOf<String?>(null) }

        // State to trigger recomposition of the events list
        var eventsList by remember { mutableStateOf(SampleData.appEvents.toList()) }

        LaunchedEffect(SampleData.appEvents.size) {
            eventsList = SampleData.appEvents.toList()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Create New Event", style = MaterialTheme.typography.h6)

            // Event Type Dropdown
            Box {
                OutlinedTextField(
                    value = selectedEventType?.displayName ?: "Select Event Type",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().clickable { eventTypeDropdownExpanded = true },
                    label = { Text("Event Type") },
                    trailingIcon = { Icon(Icons.Filled.ArrowDropDown, "Select Event Type") }
                )
                DropdownMenu(
                    expanded = eventTypeDropdownExpanded,
                    onDismissRequest = { eventTypeDropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppEventType.entries.forEach { type ->
                        DropdownMenuItem(onClick = {
                            selectedEventType = type
                            eventTypeDropdownExpanded = false
                        }) {
                            Text(type.displayName)
                        }
                    }
                }
            }

            AppTextField(
                value = notes,
                onValueChange = { notes = it },
                label = "Notes",
                modifier = Modifier.height(120.dp).fillMaxWidth() // Multi-line
            )

            AppButton(
                text = "Submit Event",
                onClick = {
                    if (selectedEventType != null && notes.isNotBlank()) {
                        val newEvent = AppEvent(
                            id = "event_${Clock.System.now().toEpochMilliseconds()}",
                            eventType = selectedEventType!!,
                            notes = notes
                        )
                        SampleData.appEvents.add(0, newEvent)
                        eventSubmissionMessage = "Event '${newEvent.eventType.displayName}' submitted successfully!"
                        // Clear form
                        selectedEventType = null
                        notes = ""
                        // Trigger list update (already handled by LaunchedEffect on SampleData.appEvents.size)
                    } else {
                        eventSubmissionMessage = "Error: Please select event type and enter notes."
                    }
                },
                enabled = selectedEventType != null && notes.isNotBlank()
            )

            eventSubmissionMessage?.let {
                Text(
                    it,
                    color = if (it.startsWith("Error:")) MaterialTheme.colors.error else MaterialTheme.colors.primary
                )
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            Text("Submitted Events", style = MaterialTheme.typography.h6)

            if (eventsList.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    Text("No events submitted yet.")
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(eventsList, key = { it.id }) { event ->
                        AppEventItemCard(event)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AppEventItemCard(event: AppEvent) {
    Card(
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                event.eventType.displayName,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Time: ${event.getFormattedTimestamp()}",
                style = MaterialTheme.typography.caption,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(event.notes, style = MaterialTheme.typography.body2)
        }
    }
}
