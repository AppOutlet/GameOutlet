package appoutlet.gameoutlet.repository.game

import appoutlet.gameoutlet.core.database.GameEntity
import appoutlet.gameoutlet.domain.Game

class GameEntityMapper {
    operator fun invoke(game: Game) = GameEntity(
        id = game.id,
        title = game.title,
        imageUrl = game.image,
    )
}
