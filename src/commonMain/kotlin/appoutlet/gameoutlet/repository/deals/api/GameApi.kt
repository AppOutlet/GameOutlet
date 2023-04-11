package appoutlet.gameoutlet.repository.deals.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {
    @GET("games")
    suspend fun findById(@Query("id") id: Long): GameResponse
}
