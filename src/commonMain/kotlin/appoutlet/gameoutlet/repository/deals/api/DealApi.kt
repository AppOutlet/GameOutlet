package appoutlet.gameoutlet.repository.deals.api

import retrofit2.http.GET

interface DealApi {
    @GET("deals?onSale=1")
    suspend fun findLatestDeals(): List<DealResponse>
}
