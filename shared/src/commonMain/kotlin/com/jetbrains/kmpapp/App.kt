package com.jetbrains.kmpapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.list.ListScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route

@Serializable
data object ListRoute : Route

@Serializable
data class DetailRoute(val objectId: Int) : Route

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            val backStack = rememberSerializable(serializer = SnapshotStateListSerializer()) {
                mutableStateListOf<Route>(ListRoute)
            }

            val onBack = { if (backStack.size > 1) backStack.removeLast() }
            NavDisplay(
                backStack = backStack,
                onBack = onBack,
                entryProvider = entryProvider {
                    entry<ListRoute> {
                        ListScreen(navigateToDetails = { objectId ->
                            backStack.add(DetailRoute(objectId))
                        })
                    }
                    entry<DetailRoute> { destination ->
                        DetailScreen(
                            objectId = destination.objectId,
                            navigateBack = onBack,
                        )
                    }
                },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                ),
            )
        }
    }
}
