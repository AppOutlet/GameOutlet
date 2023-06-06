package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.feature.common.ViewModel

class SettingsViewModel : ViewModel<SettingsUiState, SettingsInputEvent>(initialState = SettingsUiState.Idle) {
    override fun onInputEvent(inputEvent: SettingsInputEvent) {
        when (inputEvent) {
            SettingsInputEvent.LoadSettings -> TODO()
            is SettingsInputEvent.UpdateThemePreference -> TODO()
        }
    }
}
