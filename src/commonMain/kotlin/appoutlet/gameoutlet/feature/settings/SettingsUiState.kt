package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.feature.common.UiState

sealed interface SettingsUiState : UiState {
    object Idle : SettingsUiState
}
