package appoutlet.gameoutlet

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.singleWindowApplication
import appoutlet.gameoutlet.feature.splash.SplashView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition

fun main() {
    initKoin()
    singleWindowApplication(title = "GameOutlet") {
        App()
    }
}

@Suppress("ModifierMissing")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    MaterialTheme {
        Surface {
            Navigator(SplashView()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
