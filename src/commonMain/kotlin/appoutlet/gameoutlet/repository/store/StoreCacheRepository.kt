package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.repository.preference.PreferenceRepository
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class StoreCacheRepository(private val preferenceRepository: PreferenceRepository) {
    fun isStoreListCacheValid(): Boolean {
        val lastUpdateString = preferenceRepository.getPreference(KEY_STORE_LIST_CACHE)
        val lastUpdate = lastUpdateString?.let(LocalDateTime::parse)
        val cacheExpirationDate = lastUpdate?.plus(7, ChronoUnit.DAYS)
        return cacheExpirationDate?.let { expirationDate ->
            LocalDateTime.now().isAfter(expirationDate)
        } ?: false
    }

    fun registerStoreListCached() {
        preferenceRepository.setPreference(KEY_STORE_LIST_CACHE, LocalDateTime.now().toString())
    }

    companion object {
        private const val KEY_STORE_LIST_CACHE = "storeListCache"
    }
}
