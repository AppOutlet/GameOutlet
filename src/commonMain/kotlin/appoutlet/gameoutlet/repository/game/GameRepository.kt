package appoutlet.gameoutlet.repository.game

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import appoutlet.gameoutlet.core.database.GameQueries
import appoutlet.gameoutlet.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    fun deleteById(gameId: Long) {
        gameQueries.deleteById(gameId)
    }

    fun findAll(): Flow<List<Game>> = gameQueries.findAll()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { entities -> entities.map(gameMapper::invoke) }
}
