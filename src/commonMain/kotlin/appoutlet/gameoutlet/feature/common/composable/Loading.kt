package appoutlet.gameoutlet.feature.common.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing

@Composable
fun Loading(text: String = i18n.tr("Loading"), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(modifier = Modifier.width(256.dp))
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}


@Composable
@Preview
private fun LoadingPreview() {
    GameOutletTheme {
        Loading(text = "Loading latest deals")
    }
}