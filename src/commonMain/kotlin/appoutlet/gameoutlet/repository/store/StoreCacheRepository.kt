package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.util.TimeProvider
import appoutlet.gameoutlet.repository.preference.PreferenceRepository
import java.time.LocalDateTime

class StoreCacheRepository(
    private val preferenceRepository: PreferenceRepository,
    private val timeProvider: TimeProvider
) {
    fun isStoreListCacheValid(): Boolean {
        val lastUpdateString = preferenceRepository.getPreference(KEY_STORE_LIST_CACHE)
        val lastUpdate = lastUpdateString?.let(LocalDateTime::parse)
        val cacheExpirationDate = lastUpdate?.plusDays(STORE_CACHE_VALIDITY_IN_DAYS)
        return cacheExpirationDate?.let { expirationDate ->
            expirationDate.isAfter(timeProvider.now())
        } ?: false
    }

    fun registerStoreListCached() {
        preferenceRepository.setPreference(KEY_STORE_LIST_CACHE, timeProvider.now().toString())
    }

    companion object {
        const val KEY_STORE_LIST_CACHE = "storeListCache"
        const val STORE_CACHE_VALIDITY_IN_DAYS = 7L
    }
}
