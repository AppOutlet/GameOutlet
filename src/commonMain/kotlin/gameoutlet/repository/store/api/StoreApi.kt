package gameoutlet.repository.store.api

import gameoutlet.repository.store.api.model.StoreResponse
import retrofit2.http.GET

interface StoreApi {
    @GET("stores")
    suspend fun findAll(): List<StoreResponse>
}
