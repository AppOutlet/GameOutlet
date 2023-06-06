package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.feature.common.UiState

sealed interface SettingsUiState : UiState {
    object Idle : SettingsUiState
    data class Loaded(val settingsViewData: SettingsViewData) : SettingsUiState
}

data class SettingsViewData(
    val themeViewData: ThemeViewData
)

data class ThemeViewData(
    val lightButton: ThemeButtonViewData,
    val darkButton: ThemeButtonViewData,
    val systemThemeButton: ThemeButtonViewData,
) {
    data class ThemeButtonViewData(
        val name: String,
        val isSelected: Boolean,
        val inputEvent: SettingsInputEvent,
    )
}


