package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.DealRepository
import kotlinx.coroutines.flow.flow

class StoreOrchestrator(
    private val dealRepository: DealRepository
) {
    fun loadStore(store: Store) = flow { emit(dealRepository.findDealsByStore(store)) }
}