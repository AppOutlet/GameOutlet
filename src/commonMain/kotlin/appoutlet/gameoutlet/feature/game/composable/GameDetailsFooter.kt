package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing

@Composable
fun GameDetailsFooter() {
    Row(
        modifier = Modifier
            .widthIn(max = 500.dp)
            .padding(vertical = MaterialTheme.spacing.large, horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth(),
    ) {
        Icon(imageVector = Icons.Outlined.Warning, contentDescription = null)

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

        Text(
            text = i18n.tr(
                "The prices are in US dollar (USD). Note that the value can change depending on your location"
            ),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}