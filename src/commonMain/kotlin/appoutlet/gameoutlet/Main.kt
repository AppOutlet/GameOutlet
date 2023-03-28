package appoutlet.gameoutlet

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import appoutlet.gameoutlet.feature.splash.SplashView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import java.awt.Dimension

private const val WINDOW_MIN_WIDTH = 550
private const val WINDOW_MIN_HEIGHT = 500
private const val WINDOW_WIDTH = 1000
private const val WINDOW_HEIGHT = 690

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "GameOutlet",
        icon = painterResource("image/icon.png"),
        state = rememberWindowState(
            size = DpSize(WINDOW_WIDTH.dp, WINDOW_HEIGHT.dp),
        )
    ) {
        window.minimumSize = Dimension(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT)
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
