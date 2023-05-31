package appoutlet.gameoutlet.feature.store

import org.koin.dsl.module

val storeModule = module {
    factory { StoreView.Provider() }
    factory { StoreViewModel() }
}
