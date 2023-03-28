package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.repository.deals.api.DealApi

class DealRepository(private val dealApi: DealApi, private val dealMapper: DealMapper) {
    suspend fun findLatestDeals(): List<Deal>  {
        val dealsResponse = dealApi.findLatestDeals()
        return dealMapper(dealsResponse)
    }
}