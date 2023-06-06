package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.domain.Theme
import name.kropp.kotlinx.gettext.I18n
import name.kropp.kotlinx.gettext.tr

class SettingsViewDataMapper(
    private val i18n: I18n,
) {
    operator fun invoke(theme: Theme): SettingsViewData {
        return SettingsViewData(
            themeViewData = mapTheme(theme)
        )
    }

    private fun mapTheme(theme: Theme): ThemeViewData {
        return ThemeViewData(
            lightButton = ThemeViewData.ThemeButtonViewData(
                name = i18n.tr("Light"),
                isSelected = theme == Theme.LIGHT,
                inputEvent = SettingsInputEvent.UpdateThemePreference(Theme.LIGHT)
            ),
            darkButton = ThemeViewData.ThemeButtonViewData(
                name = i18n.tr("Dark"),
                isSelected = theme == Theme.DARK,
                inputEvent = SettingsInputEvent.UpdateThemePreference(Theme.DARK)
            ),
            systemThemeButton = ThemeViewData.ThemeButtonViewData(
                name = i18n.tr("System default"),
                isSelected = theme == Theme.SYSTEM,
                inputEvent = SettingsInputEvent.UpdateThemePreference(Theme.SYSTEM)
            ),
        )
    }
}
