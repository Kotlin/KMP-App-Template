package screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.MuseumObject
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

data class DetailScreen(val objectId: Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: DetailViewModel = getScreenModel()
        val obj = viewModel.getObject(objectId).collectAsState(initial = null).value
        if (obj != null) {
            ObjectDetails(obj)
        }
    }
}

@Composable
private fun ObjectDetails(obj: MuseumObject) {
    val navigator = LocalNavigator.currentOrThrow

    Column(
        Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        KamelImage(
            resource = asyncPainterResource(data = obj.primaryImageSmall),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                // TODO proper back navigation
                .clickable { navigator.pop() },
        )

        Spacer(Modifier.height(2.dp))

        Text(obj.title, style = MaterialTheme.typography.h5)
        if (obj.artistDisplayName.isNotEmpty()) {
            Text(obj.artistDisplayName)
        }
        Text(obj.objectDate, fontStyle = FontStyle.Italic)
        Text(obj.dimensions)
        Text(obj.medium)
    }
}
