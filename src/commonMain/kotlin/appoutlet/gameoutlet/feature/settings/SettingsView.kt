package appoutlet.gameoutlet.feature.settings

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.settings.composable.SettingsScreen
import org.koin.core.component.inject

class SettingsView : View<SettingsUiState, SettingsInputEvent>() {
    override val viewModel by inject<SettingsViewModel>()

    @Composable
    override fun ViewContent(uiState: SettingsUiState, onInputEvent: (SettingsInputEvent) -> Unit) {
        SettingsScreen(uiState, onInputEvent)
    }
}
