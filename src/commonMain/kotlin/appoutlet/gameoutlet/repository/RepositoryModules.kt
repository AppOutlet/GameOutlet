package appoutlet.gameoutlet.repository

import appoutlet.gameoutlet.repository.deals.dealRepositoryModule
import appoutlet.gameoutlet.repository.game.gameRepositoryModule
import appoutlet.gameoutlet.repository.preference.preferenceRepositoryModule
import appoutlet.gameoutlet.repository.store.storeRepositoryModule

val repositoryModules = arrayOf(
    storeRepositoryModule,
    preferenceRepositoryModule,
    dealRepositoryModule,
    gameRepositoryModule,
)
