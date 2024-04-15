package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface GameInputEvent : InputEvent {
    object NavigateBack : GameInputEvent
    data class Load(val gameNavArgs: GameNavArgs) : GameInputEvent
    data class DealClicked(val deal: GameDealUiModel) : GameInputEvent
    data class SaveGame(val game: Game) : GameInputEvent
    data class RemoveGameFromFavorites(val game: Game) : GameInputEvent
}
