package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.feature.common.ViewModel

class SettingsViewModel : ViewModel<SettingsUiState, SettingsInputEvent>() {
    override fun onInputEvent(inputEvent: SettingsInputEvent) {
        println(inputEvent)
    }
}