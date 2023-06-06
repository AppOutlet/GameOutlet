package appoutlet.gameoutlet

import appoutlet.gameoutlet.domain.Theme
import appoutlet.gameoutlet.repository.theme.ThemeRepository

class MainOrchestrator(
    private val themeRepository: ThemeRepository
) {
    fun getIsDarkMode(systemDefault: Boolean): Boolean {
        return when (themeRepository.getTheme()) {
            Theme.LIGHT -> false
            Theme.DARK -> true
            Theme.SYSTEM -> systemDefault
        }
    }
}
