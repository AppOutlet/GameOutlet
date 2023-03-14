package appoutlet.gameoutlet

import appoutlet.gameoutlet.core.coreModules
import appoutlet.gameoutlet.feature.featureModules
import appoutlet.gameoutlet.repository.repositoryModules
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

@Suppress("SpreadOperator")
fun initKoin(): Koin {
    return startKoin {
        logger(PrintLogger(level = Level.WARNING))
        modules(
            * coreModules,
            * featureModules,
            * repositoryModules,
        )
    }.koin
}
