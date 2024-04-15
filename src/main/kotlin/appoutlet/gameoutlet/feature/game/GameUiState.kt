package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.feature.common.UiState

sealed interface GameUiState : UiState {
    object Idle : GameUiState
    object Loading : GameUiState
    object Error : GameUiState
    data class Loaded(val gameUiModel: GameUiModel) : GameUiState
}
