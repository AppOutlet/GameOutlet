package gameoutlet.repository.store

import org.koin.dsl.module

val storeRepositoryModule = module {
    factory { StoreRepository(get()) }
}
