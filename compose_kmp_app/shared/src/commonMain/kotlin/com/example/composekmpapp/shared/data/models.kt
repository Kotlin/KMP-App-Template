package com.example.composekmpapp.shared.data

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// Simple data models for now. These could be expanded later.

data class Shift(val id: String, val name: String)

data class Location(val id: String, val name: String)

// Movement Log related models
enum class PersonType {
    STAFF, MAID, DAILY_WORKER, DELIVERY_PERSON, GOODS, RESIDENT, VISITOR, OTHER
}

enum class MovementDirection {
    ENTRY, EXIT
}

data class MovementLogEntry(
    val id: String,
    val timestamp: Instant = Clock.System.now(),
    val personName: String,
    val personType: PersonType,
    val direction: MovementDirection,
    val vehicleDetails: String? = null,
    val purpose: String? = null,
    val remarks: String? = null
) {
    fun getFormattedTimestamp(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
        val localDateTime = timestamp.toLocalDateTime(timeZone)
        return "${localDateTime.date} ${localDateTime.hour.toString().padStart(2, '0')}:${localDateTime.minute.toString().padStart(2, '0')}"
    }
}


// Sample data
object SampleData {
    val shifts = listOf(
        Shift("morning", "Morning (8 AM - 4 PM)"),
        Shift("evening", "Evening (4 PM - 12 AM)"),
        Shift("night", "Night (12 AM - 8 AM)")
    )

    val locations = listOf(
        Location("palace_a", "Main Palace"),
        Location("villa_b", "Guest Villa B"),
        Location("security_gate_1", "Security Gate 1")
    )

    // In-memory store for movement logs
    val movementLogs = mutableListOf<MovementLogEntry>()

    // Event related models
    enum class AppEventType(val displayName: String) {
        SECURITY_INCIDENT("Security Incident"),
        MAINTENANCE_REQUEST("Maintenance Request"),
        SYSTEM_ALERT("System Alert"),
        VISITOR_NOTIFICATION("Visitor Notification"),
        OPERATIONAL_LOG("Operational Log"), // Added for keys, instructions, etc.
        GENERAL_REPORT("General Report (Other)") // Clarified 'General Report'
    }

    data class AppEvent(
        val id: String,
        val timestamp: Instant = Clock.System.now(),
        val eventType: AppEventType,
        val notes: String
    ) {
        fun getFormattedTimestamp(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
            val localDateTime = timestamp.toLocalDateTime(timeZone)
            return "${localDateTime.date} ${localDateTime.hour.toString().padStart(2, '0')}:${localDateTime.minute.toString().padStart(2, '0')}"
        }
    }

    // In-memory store for app events
    val appEvents = mutableListOf<AppEvent>()
}
