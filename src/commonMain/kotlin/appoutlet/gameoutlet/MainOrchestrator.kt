package appoutlet.gameoutlet

import appoutlet.gameoutlet.repository.preference.PreferenceRepository

class MainOrchestrator(
    private val preferenceRepository: PreferenceRepository
) {
    fun setIsDarkMode(isDarkMode: Boolean) {
        preferenceRepository.setPreference(PREFERENCE_DARK_MODE, isDarkMode.toString())
    }

    fun getIsDarkMode(): Boolean? {
        return when(preferenceRepository.getPreference(PREFERENCE_DARK_MODE)) {
            "true" -> true
            "false" -> false
            else -> null
        }
    }

    companion object {
        const val PREFERENCE_DARK_MODE = "dark_mode"
    }
}
