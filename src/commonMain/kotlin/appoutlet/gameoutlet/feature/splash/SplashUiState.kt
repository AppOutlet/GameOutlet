package appoutlet.gameoutlet.feature.splash

import androidx.compose.runtime.Stable
import appoutlet.gameoutlet.feature.util.UiState

@Stable
sealed interface SplashUiState : UiState {
    object Idle : SplashUiState
    object Loading : SplashUiState
    object Error : SplashUiState
    object Loaded : SplashUiState
}
