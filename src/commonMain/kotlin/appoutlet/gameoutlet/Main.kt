@file:JvmName("GameOutlet")

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import appoutlet.gameoutlet.MainOrchestrator
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.feature.splash.SplashView
import appoutlet.gameoutlet.initKoin
import appoutlet.gameoutlet.initLogger
import appoutlet.gameoutlet.initLookAndFeel
import appoutlet.gameoutlet.setupWindowLookAndFeel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import java.awt.Dimension

private const val WINDOW_MIN_WIDTH = 750
private const val WINDOW_MIN_HEIGHT = 500
private const val WINDOW_WIDTH = 1000
private const val WINDOW_HEIGHT = 690
private val koin = initKoin()

fun main() {
    application {
        initLogger()

        val mainOrchestrator = koin.get<MainOrchestrator>()
        val isDarkTheme by mainOrchestrator.isDarkTheme(isSystemInDarkTheme()).collectAsState(isSystemInDarkTheme())

        initLookAndFeel(isDarkTheme)

        Window(
            onCloseRequest = ::exitApplication,
            title = "GameOutlet",
            icon = painterResource("image/icon.png"),
            state = rememberWindowState(
                size = DpSize(WINDOW_WIDTH.dp, WINDOW_HEIGHT.dp),
            )
        ) {
            setupWindowLookAndFeel()
            window.minimumSize = Dimension(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT)
            App(isDarkTheme)
        }
    }
}

@Suppress("ModifierMissing")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(isDarkTheme: Boolean) {
    GameOutletTheme(useDarkTheme = isDarkTheme) {
        Surface {
            Navigator(SplashView()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
