import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import appoutlet.gameoutlet.feature.splash.SplashNavigation
import appoutlet.gameoutlet.feature.splash.SplashScreen
import appoutlet.gameoutlet.initKoin
import cafe.adriel.voyager.navigator.Navigator

fun main() {
    val koin = initKoin()
    val defaultScreen = koin.get<SplashNavigation>().getScreen()
    application {
        Window(onCloseRequest = ::exitApplication) {
            App(defaultScreen)
        }
    }
}

@Composable
fun App(defaultScreen: SplashScreen) {
    MaterialTheme {
        Navigator(defaultScreen)
    }
}
