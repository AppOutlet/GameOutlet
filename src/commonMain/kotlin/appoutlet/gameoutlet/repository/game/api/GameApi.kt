package appoutlet.gameoutlet.repository.game.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {
    @GET("games")
    fun findById(@Query("id") id: Long): GameResponse
}