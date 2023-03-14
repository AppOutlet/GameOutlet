package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.feature.util.UiState

sealed interface SplashUiState : UiState {
    object Idle : SplashUiState
}
