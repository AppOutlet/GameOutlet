package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.repository.deals.DealRepository
import appoutlet.gameoutlet.repository.game.GameRepository
import appoutlet.gameoutlet.repository.store.StoreRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GameOrchestrator(
    private val storeRepository: StoreRepository,
    private val dealRepository: DealRepository,
    private val gameRepository: GameRepository,
) {
    fun findDealsByGame(game: Game): Flow<List<Deal>> = flow {
        val deals = dealRepository.findDealsByGame(game)
        emit(deals)
    }.map { addStoreDetails(it) }

    private suspend fun addStoreDetails(deals: List<Deal>): List<Deal> = coroutineScope {
        val deferredDeals = deals.map { deal ->
            async {
                val store = storeRepository.findById(deal.store.id)
                store?.let {
                    deal.copy(store = it)
                }
            }
        }

        deferredDeals.awaitAll().filterNotNull()
    }

    fun save(game: Game) {
        gameRepository.save(game)
    }

    fun checkIfGameIsSaved(game: Game): Boolean {
        val savedGame = gameRepository.findById(game.id)
        return savedGame?.let { true } ?: false
    }
}
