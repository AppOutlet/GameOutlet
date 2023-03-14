package appoutlet.gameoutlet.feature.splash

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.util.FirstLoad
import appoutlet.gameoutlet.feature.util.View
import org.koin.core.component.inject

class SplashView : View<SplashUiState, SplashInputEvent>() {
    override val viewModel by inject<SplashViewModel>()
    override val initialState = SplashUiState.Idle

    @Composable
    override fun ViewContent(uiState: SplashUiState, onInputEvent: (SplashInputEvent) -> Unit) =
        SplashScreenContent(uiState, onInputEvent)
}

@Composable
private fun SplashScreenContent(uiState: SplashUiState, onInputEvent: (SplashInputEvent) -> Unit) {
    FirstLoad {
        onInputEvent(SplashInputEvent.Load)
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.weight(1f)) { }
        Text(modifier = Modifier.padding(vertical = 16.dp), text = i18n.tr("Powered by App Outlet"))
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreenContent(uiState = SplashUiState.Idle, onInputEvent = {})
}
