@file:JvmName("GameOutlet")

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import appoutlet.gameoutlet.*
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.feature.splash.SplashView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.jthemedetecor.OsThemeDetector
import java.awt.Dimension

private const val WINDOW_MIN_WIDTH = 750
private const val WINDOW_MIN_HEIGHT = 500
private const val WINDOW_WIDTH = 1000
private const val WINDOW_HEIGHT = 690
private val koin = initKoin()

fun main() {
    application {
        initLogger()
        var isDarkThemeSystemDefault by remember { mutableStateOf(isSystemInDarkThemeSecure()) }
        OsThemeDetector.getDetector().registerListener {
            isDarkThemeSystemDefault = it
        }

        val mainOrchestrator = koin.get<MainOrchestrator>()
        val isDarkTheme by mainOrchestrator
            .isDarkTheme(isDarkThemeSystemDefault)
            .collectAsState(isDarkThemeSystemDefault)

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
