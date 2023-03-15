package appoutlet.gameoutlet.feature.splash.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme

@Composable
fun SplashLoadingIndicator(isLoading: Boolean) {
    AnimatedVisibility(visible = isLoading) {
        Column {
            LinearProgressIndicator()
            Text(i18n.tr("Loading"))
        }
    }
}

@Composable
@Preview
private fun SplashLoadingIndicatorPreview() {
    GameOutletTheme {
        SplashLoadingIndicator(true)
    }
}
