package appoutlet.gameoutlet

import appoutlet.gameoutlet.domain.Theme
import appoutlet.gameoutlet.repository.theme.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class MainOrchestrator(
    private val themeRepository: ThemeRepository
) {
    private val _theme = MutableStateFlow(themeRepository.getTheme())

    init {
        themeRepository.observeTheme { currentTheme ->
            _theme.value = currentTheme
        }
    }

    fun isDarkTheme(systemDefault: Boolean): Flow<Boolean> {
        return _theme.asStateFlow()
            .map {
                when(it) {
                    Theme.LIGHT -> false
                    Theme.DARK -> true
                    Theme.SYSTEM -> systemDefault
                }
            }
    }
}
