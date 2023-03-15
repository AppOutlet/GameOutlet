package appoutlet.gameoutlet.repository.preference

import appoutlet.gameoutlet.core.database.PreferenceQueries

class PreferenceRepository(
    private val preferenceQueries: PreferenceQueries
) {
    fun setPreference(key: String, value: String) {
        preferenceQueries.save(key, value)
    }

    fun getPreference(key: String): String? {
        return preferenceQueries.findByKey(key).executeAsOneOrNull()?.value_
    }
}
