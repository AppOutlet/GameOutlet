package appoutlet.gameoutlet.repository.game

import appoutlet.gameoutlet.core.database.GameQueries
import appoutlet.gameoutlet.domain.Game

class GameRepository(
    private val gameQueries: GameQueries,
    private val gameEntityMapper: GameEntityMapper,
    private val gameMapper: GameMapper,
) {
    fun save(game: Game) {
        val gameEntity = gameEntityMapper(game)
        gameQueries.save(gameEntity)
    }

    fun findById(gameId: Long): Game? {
        val entity = gameQueries.findById(gameId).executeAsOneOrNull()
        return entity?.let(gameMapper::invoke)
    }
}