package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.domain.Theme
import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.repository.theme.ThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class SettingsViewModel(
    private val themeRepository: ThemeRepository,
    private val settingsViewDataMapper: SettingsViewDataMapper,
) : ViewModel<SettingsUiState, SettingsInputEvent>(initialState = SettingsUiState.Idle) {
    private val theme = MutableStateFlow(themeRepository.getTheme())

    override fun afterViewModelInitialization() {
        super.afterViewModelInitialization()

        themeRepository.observeTheme { currentTheme ->
            theme.value = currentTheme
        }

        viewModelJob = theme
            .map { settingsViewDataMapper(it) }
            .onEach { mutableUiState.value = SettingsUiState.Loaded(it) }
            .launchIn(viewModelScope)
    }

    override fun onInputEvent(inputEvent: SettingsInputEvent) {
        when (inputEvent) {
            SettingsInputEvent.LoadSettings -> {}
            is SettingsInputEvent.UpdateThemePreference -> updateTheme(inputEvent.theme)
        }
    }

    private fun updateTheme(theme: Theme) {
        themeRepository.setTheme(theme)
    }
}
