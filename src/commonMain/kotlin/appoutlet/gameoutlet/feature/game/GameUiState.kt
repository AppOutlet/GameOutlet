package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.feature.common.UiState

sealed interface GameUiState : UiState {
    object Idle : GameUiState
}