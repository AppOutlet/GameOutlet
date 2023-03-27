package appoutlet.gameoutlet.repository.deals.api

import retrofit2.http.GET
import retrofit2.http.Query

interface DealApi {
    @GET("deals")
    suspend fun findLatestDeals(@Query("onSale") onSale: Int): List<DealResponse>
}