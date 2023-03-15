package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.repository.preference.PreferenceRepository
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class StoreCacheRepository(private val preferenceRepository: PreferenceRepository) {
    fun isStoreListCacheValid(): Boolean {
        val lastUpdateString = preferenceRepository.getPreference(KEY_STORE_LIST_CACHE)
        val lastUpdate = lastUpdateString?.let(LocalDateTime::parse)
        val cacheExpirationDate = lastUpdate?.plus(STORE_CACHE_VALIDITY_IN_DAYS, ChronoUnit.DAYS)
        return cacheExpirationDate?.let { expirationDate ->
            val now = LocalDateTime.now()
            expirationDate.isAfter(now)
        } ?: false
    }

    fun registerStoreListCached() {
        preferenceRepository.setPreference(KEY_STORE_LIST_CACHE, LocalDateTime.now().toString())
    }

    companion object {
        private const val KEY_STORE_LIST_CACHE = "storeListCache"
        private const val STORE_CACHE_VALIDITY_IN_DAYS = 7L
    }
}
