package gameoutlet.feature.splash

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
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreen : Screen, KoinComponent {
    private val splashViewModel by inject<SplashViewModel>()

    @Composable
    override fun Content() {
        SplashScreenContent(splashViewModel.message)
    }
}

@Composable
private fun SplashScreenContent(message: String) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.weight(1f)) { }
        Text(modifier = Modifier.padding(vertical = 16.dp), text = message)
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreenContent("preview message")
}
