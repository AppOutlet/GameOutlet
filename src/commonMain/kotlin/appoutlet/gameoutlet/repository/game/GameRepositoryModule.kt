package appoutlet.gameoutlet.repository.game

import org.koin.dsl.module

val gameRepositoryModule = module {
    factory { GameRepository() }
}