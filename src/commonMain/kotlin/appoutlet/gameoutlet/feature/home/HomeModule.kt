package appoutlet.gameoutlet.feature.home

import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel() }
}
