package appoutlet.gameoutlet.repository

import appoutlet.gameoutlet.repository.deals.dealsRepositoryModule
import appoutlet.gameoutlet.repository.preference.preferenceRepositoryModule
import appoutlet.gameoutlet.repository.store.storeRepositoryModule

val repositoryModules = arrayOf(
    storeRepositoryModule,
    preferenceRepositoryModule,
    dealsRepositoryModule,
)
