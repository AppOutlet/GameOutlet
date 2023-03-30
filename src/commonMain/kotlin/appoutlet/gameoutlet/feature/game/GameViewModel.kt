package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.feature.common.ViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class GameViewModel(
    private val gameOrchestrator: GameOrchestrator,
) : ViewModel<GameUiState, GameInputEvent>(initialState = GameUiState.Idle) {
    override fun onInputEvent(inputEvent: GameInputEvent) {
        when (inputEvent) {
            GameInputEvent.NavigateBack -> navigator.pop()
            is GameInputEvent.Load -> loadGame(inputEvent.gameId)
        }
    }

    private fun loadGame(gameId: Long) {
        gameOrchestrator.findById(gameId)
            .onStart { mutableUiState.value = GameUiState.Loading }
            .catch { mutableUiState.value = GameUiState.Error }
            .onEach { mutableUiState.value = GameUiState.Loaded() }
            .launchIn(viewModelScope)
    }
}
