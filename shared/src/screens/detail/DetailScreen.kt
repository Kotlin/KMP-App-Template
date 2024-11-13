package com.jetbrains.kmpapp.screens.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

data class DetailScreen(val objectId: Int) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: DetailScreenModel = getScreenModel()

        val obj by screenModel.getObject(objectId).collectAsStateWithLifecycle(initialValue = null)
        AnimatedContent(obj != null) { objectAvailable ->
            if (objectAvailable) {
                ObjectDetails(obj!!, onBackClick = { navigator.pop() })
            } else {
                EmptyScreenContent(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun ObjectDetails(
    obj: MuseumObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars),
    ) { paddingValues ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            KamelImage(
                resource = asyncPainterResource(data = obj.primaryImageSmall),
                contentDescription = obj.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )

            SelectionContainer {
                Column(Modifier.padding(12.dp)) {
                    Text(obj.title, style = MaterialTheme.typography.headlineMedium)
                    Spacer(Modifier.height(6.dp))
                    LabeledInfo("Title", obj.title)
                    LabeledInfo("Artist", obj.artistDisplayName)
                    LabeledInfo("Date", obj.objectDate)
                    LabeledInfo("Dimensions", obj.dimensions)
                    LabeledInfo("Medium", obj.medium)
                    LabeledInfo("Department", obj.department)
                    LabeledInfo("Repository", obj.repository)
                    LabeledInfo("Credits", obj.creditLine)
                }
            }
        }
    }
}

@Composable
private fun LabeledInfo(
    label: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(vertical = 4.dp)) {
        Spacer(Modifier.height(6.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label: ")
                }
                append(data)
            }
        )
    }
}
