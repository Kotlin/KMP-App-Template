package com.jetbrains.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.list.ListScreen

@Composable
fun App() {
    MaterialTheme {
        val navController: NavHostController = rememberNavController()
        NavHost(
            navController,
            startDestination = "list"
        ) {
            composable("list") {
                ListScreen(navController)
            }
            composable("detail/{objectId}") { backStackEntry ->
                val objectId = backStackEntry.arguments?.getString("objectId")?.toInt()
                DetailScreen(navController, objectId!!)
            }
        }
    }
}
