package appoutlet.gameoutlet

import org.koin.dsl.module

val mainModule = module {
    factory { MainOrchestrator(get()) }
}
