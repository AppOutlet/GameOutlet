package appoutlet.gameoutlet.feature.gamesearch

import androidx.compose.runtime.Immutable
import appoutlet.gameoutlet.feature.common.UiState

sealed interface GameSearchUiState : UiState {
    @Immutable
    data class Idle(val searchTerm: String) : GameSearchUiState

    @Immutable
    data class Loading(val searchTerm: String, val games: List<GameSearchUiModel>) : GameSearchUiState

    @Immutable
    data class Loaded(val searchTerm: String, val games: List<GameSearchUiModel>) : GameSearchUiState
}
