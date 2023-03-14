package gameoutlet.core

import gameoutlet.core.database.databaseModule
import gameoutlet.core.translation.translationModule

val coreModules = arrayOf(
    translationModule,
    databaseModule,
)
