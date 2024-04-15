package appoutlet.gameoutlet.repository.deals.api

import retrofit2.http.GET
import retrofit2.http.Query

interface DealApi {
    @GET("deals?onSale=1")
    suspend fun findLatestDeals(): List<DealResponse>

    @GET("deals?onSale=1")
    suspend fun findDealsByStore(@Query("storeID") storeId: Int): List<DealResponse>
}
