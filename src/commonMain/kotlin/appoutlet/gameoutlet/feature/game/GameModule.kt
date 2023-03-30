package appoutlet.gameoutlet.feature.game

import org.koin.dsl.module

val gameModule = module {
    factory { GameViewModel() }
    factory { GameViewProvider() }
    factory { GameOrchestrator() }
}
