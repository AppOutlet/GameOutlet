package appoutlet.gameoutlet.feature.home

import appoutlet.gameoutlet.feature.util.UiState

sealed interface HomeUiState : UiState {
    object Idle : HomeUiState
}
