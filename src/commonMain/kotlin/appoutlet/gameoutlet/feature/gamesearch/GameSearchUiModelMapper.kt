package appoutlet.gameoutlet.feature.gamesearch

import appoutlet.gameoutlet.domain.Game

class GameSearchUiModelMapper {
    operator fun invoke(game: Game): GameSearchUiModel {
        return GameSearchUiModel(
            id = game.id,
            title = game.title,
            image = game.image,
        )
    }
}
