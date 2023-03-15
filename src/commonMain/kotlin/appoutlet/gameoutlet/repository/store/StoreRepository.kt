package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.database.StoreQueries
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.store.api.StoreApi

class StoreRepository(
    private val storeQueries: StoreQueries,
    private val storeApi: StoreApi,
    private val storeCacheRepository: StoreCacheRepository,
    private val storeMapper: StoreMapper,
    private val storeEntityMapper: StoreEntityMapper,
) {
    suspend fun findAll(): List<Store> {
        return if (storeCacheRepository.isStoreListCacheValid()) {
            findAllFromCache()
        } else {
            findAllFromApi()
        }
    }

    private fun findAllFromCache(): List<Store> {
        return storeQueries.findAll().executeAsList().map(storeMapper::invoke)
    }

    private suspend fun findAllFromApi(): List<Store> {
        val stores = storeApi.findAll()
            .asSequence()
            .filter { it.isActive == 1 }
            .map(storeMapper::invoke)
            .onEach { store ->
                val entity = storeEntityMapper(store)
                storeQueries.save(entity)
            }
            .toList()

        storeCacheRepository.registerStoreListCached()

        return stores
    }
}
