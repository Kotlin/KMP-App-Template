package com.example.composekmpapp.shared.screens.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.composekmpapp.shared.screens.main.tabs.EventsTabContentScreen
import com.example.composekmpapp.shared.screens.main.tabs.HomeTabContentScreen
import com.example.composekmpapp.shared.screens.main.tabs.MovementLogTabContentScreen
import com.example.composekmpapp.shared.screens.main.tabs.SettingsTabContentScreen

// Define individual Tab objects
// These will internally refer to the actual content screens

internal object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Home"
            val icon = Icons.Default.Home
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        // This Composable will host the actual content for the Home tab.
        // We can use another Navigator here if Home itself has sub-screens,
        // or directly embed HomeTabContentScreen.
        // For now, directly embedding.
        HomeTabContentScreen.Content()
    }
}

internal object MovementLogTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Movement Log"
            val icon = Icons.Default.ListAlt // Example icon
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        MovementLogTabContentScreen.Content()
    }
}

internal object EventsTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Events"
            val icon = Icons.Default.Event
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        EventsTabContentScreen.Content()
    }
}

internal object SettingsTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Settings"
            val icon = Icons.Default.Settings
            return remember {
                TabOptions(
                    index = 3u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        SettingsTabContentScreen.Content()
    }
}

// List of all tabs for easy access in TabNavigator
val bottomNavTabs = listOf(HomeTab, MovementLogTab, EventsTab, SettingsTab)
