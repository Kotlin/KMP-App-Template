package com.jetbrains.kmpapp.screens.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

data object ListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: ListScreenModel = getScreenModel()

        val objects by screenModel.objects.collectAsStateWithLifecycle()

        AnimatedContent(objects.isNotEmpty()) { objectsAvailable ->
            if (objectsAvailable) {
                ObjectGrid(
                    objects = objects,
                    onObjectClick = { objectId ->
                        navigator.push(DetailScreen(objectId))
                    }
                )
            } else {
                EmptyScreenContent(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun ObjectGrid(
    objects: List<MuseumObject>,
    onObjectClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        // TODO simplify padding after https://issuetracker.google.com/issues/365052672 is fixed
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal).asPaddingValues()),
        contentPadding = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical).asPaddingValues(),
    ) {
        items(objects, key = { it.objectID }) { obj ->
            ObjectFrame(
                obj = obj,
                onClick = { onObjectClick(obj.objectID) },
            )
        }
    }
}

@Composable
private fun ObjectFrame(
    obj: MuseumObject,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        KamelImage(
            resource = asyncPainterResource(data = obj.primaryImageSmall),
            contentDescription = obj.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.LightGray),
        )

        Spacer(Modifier.height(2.dp))

        Text(obj.title, style = MaterialTheme.typography.titleMedium)
        Text(obj.artistDisplayName, style = MaterialTheme.typography.bodyMedium)
        Text(obj.objectDate, style = MaterialTheme.typography.bodySmall)
    }
}
