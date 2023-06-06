package appoutlet.gameoutlet.feature.settings.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.settings.SettingsInputEvent
import appoutlet.gameoutlet.feature.settings.SettingsUiState

@Suppress("UnusedParameter")
@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onInputEvent: (SettingsInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ScreenTitle(i18n.tr("Settings"))
    }
}
