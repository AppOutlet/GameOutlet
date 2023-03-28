package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface GameInputEvent : InputEvent {
    object NavigateBack: GameInputEvent
}