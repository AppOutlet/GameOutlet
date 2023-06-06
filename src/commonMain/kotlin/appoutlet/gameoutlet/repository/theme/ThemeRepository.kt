package appoutlet.gameoutlet.repository.theme

import appoutlet.gameoutlet.domain.Theme
import appoutlet.gameoutlet.repository.preference.PreferenceRepository

class ThemeRepository(private val preferenceRepository: PreferenceRepository) {
    fun setTheme(theme: Theme) {
        preferenceRepository.setPreference(PREFERENCE_DARK_MODE, theme.name)
    }

    fun getTheme(): Theme {
        val themeString = preferenceRepository.getPreference(PREFERENCE_DARK_MODE)
        return Theme.fromString(themeString)
    }

    fun observeTheme(
        block: (Theme) -> Unit,
    ) = preferenceRepository.observePreference(PREFERENCE_DARK_MODE) { themeString ->
        block(Theme.fromString(themeString))
    }

    companion object {
        const val PREFERENCE_DARK_MODE = "dark_mode"
    }
}
