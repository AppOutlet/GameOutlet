package appoutlet.gameoutlet.feature.storelist

import org.koin.dsl.module

val storeListModule = module {
    factory { StoreListViewModel() }
}
