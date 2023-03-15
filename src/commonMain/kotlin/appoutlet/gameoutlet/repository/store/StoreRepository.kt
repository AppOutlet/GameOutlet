package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.database.StoreQueries
import appoutlet.gameoutlet.repository.store.api.StoreApi

class StoreRepository(private val storeQueries: StoreQueries, private val storeApi: StoreApi) {
    suspend fun findAll() {
        storeQueries.findAll()
    }
}
