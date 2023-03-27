package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.repository.deals.api.DealApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DealRepository(private val dealApi: DealApi, private val dealMapper: DealMapper) {
    fun findLatestDeals(): Flow<List<Deal>> =
        flow { emit(dealApi.findLatestDeals()) }
            .map { dealMapper(it) }
}