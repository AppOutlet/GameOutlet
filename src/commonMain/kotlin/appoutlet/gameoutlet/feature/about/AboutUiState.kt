package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.feature.common.UiState

sealed interface AboutUiState : UiState {
    object Idle : AboutUiState
}
