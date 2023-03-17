package appoutlet.gameoutlet

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.singleWindowApplication
import appoutlet.gameoutlet.feature.splash.SplashNavigation
import appoutlet.gameoutlet.feature.splash.SplashView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition

fun main() {
    val koin = initKoin()
    val defaultScreen = koin.get<SplashNavigation>().getScreen()
    singleWindowApplication(title = "GameOutlet") {
        App(defaultScreen)
    }
}

@Suppress("ModifierMissing")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(defaultScreen: SplashView) {
    MaterialTheme {
        Surface {
            Navigator(defaultScreen) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
