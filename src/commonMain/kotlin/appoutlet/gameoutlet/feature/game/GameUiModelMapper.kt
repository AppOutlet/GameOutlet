package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game

class GameUiModelMapper {
    operator fun invoke(game: Game, deals: List<Deal>): GameUiModel {}
}
