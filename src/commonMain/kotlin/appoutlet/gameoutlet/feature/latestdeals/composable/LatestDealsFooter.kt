package appoutlet.gameoutlet.feature.latestdeals.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsInputEvent

@Composable
fun LatestDealsFooter(modifier: Modifier = Modifier, onInputEvent: (LatestDealsInputEvent) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(top = MaterialTheme.spacing.large, bottom = MaterialTheme.spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = i18n.tr("Didn't find what you were looking for?"),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = i18n.tr("You can search by your favorite games in the search screen!"),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Button(
            onClick = {
                onInputEvent(LatestDealsInputEvent.ToSearch)
            },
            content = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Text(text = i18n.tr("Take me to search screen"))
            }
        )
    }
}

@Composable
@Preview
fun LatestDealsFooterPreview() {
    GameOutletTheme {
        LatestDealsFooter(onInputEvent = { })
    }
}
