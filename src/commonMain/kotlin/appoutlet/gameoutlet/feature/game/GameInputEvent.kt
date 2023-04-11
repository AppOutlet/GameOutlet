package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface GameInputEvent : InputEvent {
    object NavigateBack : GameInputEvent
    data class Load(val gameNavArgs: GameNavArgs) : GameInputEvent
    data class DealClicked(val deal: GameDealUiModel) : GameInputEvent
}
