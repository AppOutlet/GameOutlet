package appoutlet.gameoutlet.feature.common.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing

@Composable
fun ScreenTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.semantics { testTag = "screenTitle" }
            .padding(
                bottom = MaterialTheme.spacing.small,
                top = MaterialTheme.spacing.large,
                start = MaterialTheme.spacing.small
            ),
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
@Preview
fun PageTitlePreview() {
    GameOutletTheme {
        ScreenTitle("Page title")
    }
}
