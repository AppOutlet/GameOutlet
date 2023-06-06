package appoutlet.gameoutlet.feature.settings.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
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
        when (uiState) {
            is SettingsUiState.Loaded -> SettingsControls(
                uiState = uiState,
                onInputEvent = onInputEvent,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            else -> {}
        }
    }
}

@Composable
private fun SettingsControls(
    uiState: SettingsUiState.Loaded,
    onInputEvent: (SettingsInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.widthIn(max = 512.dp)
            .padding(all = MaterialTheme.spacing.small)
    ) {
        ThemeSelector(viewData = uiState.settingsViewData.themeViewData, onInputEvent = onInputEvent)
    }
}
