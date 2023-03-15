package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SplashOrchestrator(
    private val storeRepository: StoreRepository
) {
    fun synchronizeStoreData() : Flow<Unit> = flow {
        storeRepository.findAll()
        Unit
    }
}
