package com.example.composekmpapp.shared.data

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class MovementLogEntryTest {

    @Test
    fun testGetFormattedTimestamp() {
        // Specific point in time: 2023-10-27 10:30:00 UTC
        val specificInstant = Instant.parse("2023-10-27T10:30:00Z")
        val entry = MovementLogEntry(
            id = "test1",
            timestamp = specificInstant,
            personName = "Test Person",
            personType = PersonType.VISITOR,
            direction = MovementDirection.ENTRY
        )

        // Test with UTC TimeZone
        val formattedUtc = entry.getFormattedTimestamp(TimeZone.UTC)
        assertEquals("2023-10-27 10:30", formattedUtc, "Timestamp formatting in UTC failed.")

        // Test with a specific offset TimeZone, e.g., GMT+2
        val gmtPlus2 = TimeZone.of("GMT+2") // Or "Europe/Berlin" etc. if available
        val formattedGmtPlus2 = entry.getFormattedTimestamp(gmtPlus2)
        assertEquals("2023-10-27 12:30", formattedGmtPlus2, "Timestamp formatting in GMT+2 failed.")

         // Test with another specific offset TimeZone, e.g., GMT-5 (EST if not considering DST)
        val gmtMinus5 = TimeZone.of("GMT-5") // Or "America/New_York" etc. if available
        val formattedGmtMinus5 = entry.getFormattedTimestamp(gmtMinus5)
        assertEquals("2023-10-27 05:30", formattedGmtMinus5, "Timestamp formatting in GMT-5 failed.")
    }

    @Test
    fun testGetFormattedTimestampHandlesPadding() {
        // Specific point in time: 2023-01-05 08:05:00 UTC (single digit month, day, hour, minute)
        val specificInstant = Instant.parse("2023-01-05T08:05:00Z")
        val entry = MovementLogEntry(
            id = "test2",
            timestamp = specificInstant,
            personName = "Test Person 2",
            personType = PersonType.STAFF,
            direction = MovementDirection.EXIT
        )
        val formattedUtc = entry.getFormattedTimestamp(TimeZone.UTC)
        assertEquals("2023-01-05 08:05", formattedUtc, "Timestamp padding failed for single digit hour/minute.")
    }
}
