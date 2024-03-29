package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.domain.Theme
import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface SettingsInputEvent : InputEvent {
    data class UpdateThemePreference(val theme: Theme) : SettingsInputEvent
}
