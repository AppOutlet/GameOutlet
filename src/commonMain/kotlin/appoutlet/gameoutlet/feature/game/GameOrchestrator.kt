package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.repository.game.GameRepository
import appoutlet.gameoutlet.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

class GameOrchestrator(
    private val gameRepository: GameRepository,
    private val storeRepository: StoreRepository,
) {
    fun findById(gameId: Long): Flow<Pair<Game, List<Deal>>> {

    }
}