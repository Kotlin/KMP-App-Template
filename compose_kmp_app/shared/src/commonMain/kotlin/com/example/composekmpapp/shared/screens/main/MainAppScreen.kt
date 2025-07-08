package com.example.composekmpapp.shared.screens.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.composekmpapp.shared.screens.main.navigation.HomeTab
import com.example.composekmpapp.shared.screens.main.navigation.bottomNavTabs

object MainAppScreen : Screen {
    @Composable
    override fun Content() {
        // The first tab in the list will be the initial selected tab.
        TabNavigator(HomeTab) { tabNavigator ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(tabNavigator.current.options.title) }
                    )
                },
                content = { paddingValues ->
                    // CurrentTab will render the Content of the active Tab
                    Box(modifier = Modifier.padding(paddingValues)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colors.surface,
                        contentColor = MaterialTheme.colors.primary
                    ) {
                        // Iterate through your defined tabs
                        bottomNavTabs.forEach { tab ->
                            BottomNavigationItem(tab)
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.BottomNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    BottomNavigationItem(
        selected = isSelected,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title) },
        icon = {
            tab.options.icon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = tab.options.title
                )
            }
        },
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    )
}
