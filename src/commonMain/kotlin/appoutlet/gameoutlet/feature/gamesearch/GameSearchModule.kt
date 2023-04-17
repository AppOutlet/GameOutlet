package appoutlet.gameoutlet.feature.gamesearch

import org.koin.dsl.module

val gameSearchModule = module {
    factory { GameSearchViewModel(get(), get()) }
    factory { GameSearchUiModelMapper() }
}
