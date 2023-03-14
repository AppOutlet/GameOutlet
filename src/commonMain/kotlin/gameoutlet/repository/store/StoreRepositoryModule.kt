package gameoutlet.repository.store

import gameoutlet.repository.store.api.StoreApi
import org.koin.dsl.module
import retrofit2.Retrofit

val storeRepositoryModule = module {
    factory { StoreRepository(get(), get()) }

    factory<StoreApi> {
        val retrofit by inject<Retrofit>()
        retrofit.create(StoreApi::class.java)
    }
}
