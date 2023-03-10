package appoutlet.gameoutlet

import appoutlet.gameoutlet.core.coreModules
import appoutlet.gameoutlet.feature.featureModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

fun initKoin() {
    startKoin {
        logger(PrintLogger(level = Level.WARNING))
        modules(
            * coreModules,
            * featureModules
        )
    }
}
