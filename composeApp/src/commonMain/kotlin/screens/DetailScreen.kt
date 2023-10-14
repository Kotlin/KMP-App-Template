package screens

import MuseumObject
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

data class DetailScreen(val obj: MuseumObject) : Screen {
    @Composable
    override fun Content() {
        // TODO proper detail screen UI implementation
        ObjectFrame(obj, onClick = {})
    }
}
