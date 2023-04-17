package appoutlet.gameoutlet.feature.gamesearch

import androidx.compose.runtime.Immutable
import appoutlet.gameoutlet.feature.common.UiState

@Immutable
sealed interface GameSearchUiState : UiState {
    val searchTerm: String
    val games: List<GameSearchUiModel>

    @Immutable
    data class Idle(override val searchTerm: String) : GameSearchUiState {
        override val games = emptyList<GameSearchUiModel>()
    }

    @Immutable
    data class Loading(override val searchTerm: String, override val games: List<GameSearchUiModel>) : GameSearchUiState

    @Immutable
    data class Loaded(override val searchTerm: String, override val games: List<GameSearchUiModel>) : GameSearchUiState

    @Immutable
    data class Error(override val searchTerm: String, override val games: List<GameSearchUiModel>) : GameSearchUiState
}
