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

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "GameOutlet",
        icon = painterResource("image/icon.png"),
        state = rememberWindowState(
            size = DpSize(1000.dp, 690.dp),
        )
    ) {
        window.minimumSize = Dimension(550, 500)
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
