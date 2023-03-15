package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.feature.util.UiState

sealed interface SplashUiState : UiState {
    object Idle : SplashUiState
    object Loading : SplashUiState
    object Loaded : SplashUiState
    object Error : SplashUiState
}
