package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.feature.common.ViewModel

class GameViewModel : ViewModel<GameUiState, GameInputEvent>(initialState = GameUiState.Idle) {
    override fun onInputEvent(inputEvent: GameInputEvent) {
        when(inputEvent){
            GameInputEvent.NavigateBack -> navigator.pop()
        }
    }
}