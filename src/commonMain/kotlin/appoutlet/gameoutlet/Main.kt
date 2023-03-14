package appoutlet.gameoutlet

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.singleWindowApplication
import appoutlet.gameoutlet.feature.splash.SplashNavigation
import appoutlet.gameoutlet.feature.splash.SplashView
import cafe.adriel.voyager.navigator.Navigator

fun main() {
    val koin = initKoin()
    val defaultScreen = koin.get<SplashNavigation>().getScreen()
    singleWindowApplication(title = "GameOutlet") {
        App(defaultScreen)
    }
}

@Composable
fun App(defaultScreen: SplashView) {
    MaterialTheme {
        Navigator(defaultScreen)
    }
}
