package appoutlet.gameoutlet.feature.settings.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Contrast
import androidx.compose.material.icons.outlined.ShieldMoon
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.settings.SettingsInputEvent
import appoutlet.gameoutlet.feature.settings.ThemeViewData

@Composable
fun ThemeSelector(viewData: ThemeViewData, onInputEvent: (SettingsInputEvent) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(i18n.tr("Theme"), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            ThemeButton(
                modifier = Modifier.weight(1f),
                viewData = viewData.lightButton,
                icon = Icons.Outlined.WbSunny,
                onInputEvent = onInputEvent
            )
            ThemeButton(
                modifier = Modifier.weight(1f),
                viewData = viewData.darkButton,
                icon = Icons.Outlined.ShieldMoon,
                onInputEvent = onInputEvent
            )
            ThemeButton(
                modifier = Modifier.weight(1f),
                viewData = viewData.systemThemeButton,
                icon = Icons.Outlined.Contrast,
                onInputEvent = onInputEvent
            )
        }
    }
}

@Composable
private fun ThemeButton(
    viewData: ThemeViewData.ThemeButtonViewData,
    onInputEvent: (SettingsInputEvent) -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    val colors = if (viewData.isSelected) {
        ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    } else {
        ButtonDefaults.outlinedButtonColors()
    }

    OutlinedButton(modifier = modifier, onClick = { onInputEvent(viewData.inputEvent) }, colors = colors) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = viewData.name)
        }
    }
}
