import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import appoutlet.gameoutlet.feature.splash.SplashScreen
import appoutlet.gameoutlet.initKoin

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        SplashScreen()
    }
}

fun main() {
    initKoin()
    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}
