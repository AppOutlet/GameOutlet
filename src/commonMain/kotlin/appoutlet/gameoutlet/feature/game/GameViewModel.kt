package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.feature.common.ViewModel

class GameViewModel : ViewModel<GameUiState, GameInputEvent>(initialState = GameUiState.Idle) {
    override fun onInputEvent(inputEvent: GameInputEvent) {
        when (inputEvent) {
            GameInputEvent.NavigateBack -> navigator.pop()
            is GameInputEvent.Load -> loadGame(inputEvent.gameId)
        }
    }

    private fun loadGame(gameId: Long) {
        println("Loading game: $gameId")
    }
}
