package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.repository.deals.DealRepository
import appoutlet.gameoutlet.repository.store.StoreRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LatestDealsOrchestrator(
    private val dealRepository: DealRepository,
    private val storeRepository: StoreRepository,
) {
    fun findLatestDeals() = flow { emit(dealRepository.findLatestDeals()) }
        .map { deals -> appendStores(deals) }

    private suspend fun appendStores(deals: List<Deal>): List<Deal> = coroutineScope {
        val deferredDeals = deals.map { deal ->
            async { appendStore(deal) }
        }

        deferredDeals.awaitAll().filterNotNull()
    }

    private fun appendStore(deal: Deal): Deal? {
        val store = storeRepository.findById(deal.store.id)
        return store?.let { deal.copy(store = it) }
    }
}
