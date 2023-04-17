package appoutlet.gameoutlet.feature.gamesearch

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface GameSearchInputEvent : InputEvent {
    data class Search(val title: String): GameSearchInputEvent
    data class GameClicked(val game: GameSearchUiModel): GameSearchInputEvent
}
