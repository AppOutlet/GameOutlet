package appoutlet.gameoutlet.core

import appoutlet.gameoutlet.core.database.databaseModule
import appoutlet.gameoutlet.core.network.networkModule
import appoutlet.gameoutlet.core.translation.translationModule

val coreModules = arrayOf(
    translationModule,
    databaseModule,
    networkModule,
)
