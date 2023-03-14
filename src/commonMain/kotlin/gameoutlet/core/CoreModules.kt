package gameoutlet.core

import gameoutlet.core.database.databaseModule
import gameoutlet.core.network.networkModule
import gameoutlet.core.translation.translationModule

val coreModules = arrayOf(
    translationModule,
    databaseModule,
    networkModule,
)
