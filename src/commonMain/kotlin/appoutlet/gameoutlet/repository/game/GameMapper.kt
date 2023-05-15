package appoutlet.gameoutlet.repository.game

import appoutlet.gameoutlet.core.database.GameEntity
import appoutlet.gameoutlet.domain.Game

class GameMapper {
    operator fun invoke(gameEntity: GameEntity) = Game(
        id = gameEntity.id,
        title = gameEntity.title,
        image = gameEntity.imageUrl,
    )
}