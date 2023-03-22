package appoutlet.gameoutlet.feature.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.inject

class SettingsView : View<SettingsUiState, SettingsInputEvent>() {
    override val viewModel by inject<SettingsViewModel>()
    override val initialState = SettingsUiState.Idle

    @Composable
    override fun ViewContent(uiState: SettingsUiState, onInputEvent: (SettingsInputEvent) -> Unit) {
        Text(i18n.tr("Settings"))
    }
}
