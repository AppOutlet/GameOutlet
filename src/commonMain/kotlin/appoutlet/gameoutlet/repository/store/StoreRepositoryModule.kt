package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.database.GameOutletDatabase
import appoutlet.gameoutlet.repository.store.api.StoreApi
import org.koin.dsl.module
import retrofit2.Retrofit

val storeRepositoryModule = module {
    factory { StoreRepository(get(), get(), get(), get(), get()) }

    factory<StoreApi> {
        val retrofit by inject<Retrofit>()
        retrofit.create(StoreApi::class.java)
    }

    factory { get<GameOutletDatabase>().storeQueries }

    factory { StoreCacheRepository(get(), get()) }

    factory { StoreMapper() }

    factory { StoreEntityMapper() }
}
