package appoutlet.gameoutlet.feature.game

import org.koin.dsl.module

val gameModule = module {
    factory { GameViewModel(get(), get(), get()) }
    factory { GameViewProvider() }
    factory { GameOrchestrator(get(), get()) }
    factory { GameUiModelMapper(get()) }
}
