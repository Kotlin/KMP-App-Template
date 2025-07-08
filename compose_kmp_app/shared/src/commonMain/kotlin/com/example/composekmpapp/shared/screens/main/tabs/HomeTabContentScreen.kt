package com.example.composekmpapp.shared.screens.main.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

// Sample data for the Home Screen
data class DashboardStat(val title: String, val value: String)
data class HorizontalListItem(val id: String, val title: String, val description: String)
data class EventItem(val id: String, val name: String, val time: String, val details: String)

val sampleStats = listOf(
    DashboardStat("Active Staff", "15"),
    DashboardStat("Entries Today", "45"),
    DashboardStat("Pending Events", "3")
)

val sampleHorizontalItems = List(10) {
    HorizontalListItem("item$it", "Notice Title $it", "Short description for notice $it.")
}

val sampleEvents = List(5) {
    EventItem("event$it", "Scheduled Maintenance $it", "Tomorrow at 10:00 AM", "Details about maintenance task $it.")
}


object HomeTabContentScreen : Screen {
    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Section (e.g., Quick Stats)
            item {
                StatsSection(stats = sampleStats)
            }

            // Horizontal Scrolling Section
            item {
                HorizontalListSection(title = "Important Notices", items = sampleHorizontalItems)
            }

            // Events Dashboard Section
            item {
                EventsDashboardSection(title = "Recent Events", events = sampleEvents)
            }
        }
    }
}

@Composable
fun StatsSection(stats: List<DashboardStat>) {
    Column {
        Text("Dashboard Overview", style = MaterialTheme.typography.h6, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            stats.forEach { stat ->
                StatCard(stat)
            }
        }
    }
}

@Composable
fun StatCard(stat: DashboardStat) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stat.value, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Text(stat.title, style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun HorizontalListSection(title: String, items: List<HorizontalListItem>) {
    Column {
        Text(title, style = MaterialTheme.typography.h6, modifier = Modifier.padding(bottom = 8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(items) { item ->
                HorizontalItemCard(item)
            }
        }
    }
}

@Composable
fun HorizontalItemCard(item: HorizontalListItem) {
    Card(
        elevation = 2.dp,
        modifier = Modifier.width(200.dp).height(120.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(item.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(item.description, style = MaterialTheme.typography.body2, maxLines = 3)
        }
    }
}

@Composable
fun EventsDashboardSection(title: String, events: List<EventItem>) {
    Column {
        Text(title, style = MaterialTheme.typography.h6, modifier = Modifier.padding(bottom = 8.dp))
        events.forEach { event ->
            EventDashboardCard(event)
            Spacer(modifier = Modifier.height(8.dp))
        }
        // If many events, use a LazyColumn nested here (ensure proper height constraints or use fixed number)
        // For simplicity with a few items, direct Column usage is fine.
    }
}

@Composable
fun EventDashboardCard(event: EventItem) {
    Card(
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(event.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(event.time, style = MaterialTheme.typography.caption, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(event.details, style = MaterialTheme.typography.body2)
        }
    }
}
