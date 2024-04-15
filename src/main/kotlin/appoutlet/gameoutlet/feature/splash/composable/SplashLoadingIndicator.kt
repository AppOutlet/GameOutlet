package appoutlet.gameoutlet.feature.splash.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing

@Composable
fun SplashLoadingIndicator(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(modifier = Modifier.semantics { testTag = "splashLoadingIndicator" })
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(i18n.tr("Loading"), style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
@Preview
private fun SplashLoadingIndicatorPreview() {
    GameOutletTheme {
        SplashLoadingIndicator()
    }
}
