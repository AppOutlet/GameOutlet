package appoutlet.gameoutlet.repository.preference

import appoutlet.gameoutlet.core.database.GameOutletDatabase
import org.koin.dsl.module

val preferenceRepositoryModule = module {
    factory { PreferenceRepository(get()) }
    factory { get<GameOutletDatabase>().preferenceQueries }
}
