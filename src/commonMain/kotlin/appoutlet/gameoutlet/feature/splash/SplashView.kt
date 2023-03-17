package appoutlet.gameoutlet.feature.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.home.HomeView
import appoutlet.gameoutlet.feature.splash.composable.SplashLoadingIndicator
import appoutlet.gameoutlet.feature.util.FirstLoad
import appoutlet.gameoutlet.feature.util.View
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.core.component.inject

class SplashView : View<SplashUiState, SplashInputEvent>() {
    override val viewModel by inject<SplashViewModel>()
    override val initialState = SplashUiState.Idle

    @Composable
    override fun ViewContent(uiState: SplashUiState, onInputEvent: (SplashInputEvent) -> Unit) {
        val navigator = LocalNavigator.current

        if (uiState is SplashUiState.Loaded) {
            navigator?.replaceAll(HomeView())
        }

        SplashScreenContent(uiState, onInputEvent)
    }
}

@Composable
private fun SplashScreenContent(uiState: SplashUiState, onInputEvent: (SplashInputEvent) -> Unit) {
    FirstLoad {
        onInputEvent(SplashInputEvent.Load)
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource("image/icon.png"),
                contentDescription = "Application logo",
                modifier = Modifier.size(100.dp),
            )

            Text("GameOutlet", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            AnimatedVisibility(visible = uiState is SplashUiState.Loading) {
                SplashLoadingIndicator()
            }

            AnimatedVisibility(uiState is SplashUiState.Error) {
                Error(
                    message = i18n.tr("Occurred an error when loading the store data"),
                    onTryAgain = {
                        onInputEvent(SplashInputEvent.Load)
                    },
                )
            }
        }

        Text(modifier = Modifier.padding(vertical = 16.dp), text = i18n.tr("Powered by App Outlet"))
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreenContent(uiState = SplashUiState.Loading, onInputEvent = {})
}