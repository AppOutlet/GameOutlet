package appoutlet.gameoutlet.repository.game

import appoutlet.gameoutlet.core.database.GameOutletDatabase
import org.koin.dsl.module

val gameRepositoryModule = module {
    factory { get<GameOutletDatabase>().gameQueries }
    factory { GameRepository(get(), get(), get()) }
    factory { GameEntityMapper() }
    factory { GameMapper() }
}
