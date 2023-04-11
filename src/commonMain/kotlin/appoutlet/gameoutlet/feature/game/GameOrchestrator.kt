package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.repository.deals.DealRepository
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
) {
    fun findById(game: Game): Flow<List<Deal>> = flow {
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
}
