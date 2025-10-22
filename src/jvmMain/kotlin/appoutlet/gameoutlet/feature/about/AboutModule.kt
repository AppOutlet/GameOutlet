package appoutlet.gameoutlet.feature.about

import org.koin.dsl.module

val aboutModule = module {
    factory { AboutViewModel(get(), get()) }
    factory { AboutViewDataMapper() }
}
