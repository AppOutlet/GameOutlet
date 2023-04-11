package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.repository.deals.api.DealApi
import appoutlet.gameoutlet.repository.deals.api.GameApi
import org.koin.dsl.module
import retrofit2.Retrofit

val dealRepositoryModule = module {
    factory { DealRepository(get(), get(), get(), get()) }

    factory<DealApi> {
        val retrofit by inject<Retrofit>()
        retrofit.create(DealApi::class.java)
    }

    factory { DealGameMapper() }

    factory { DealStoreMapper() }

    factory { DealMapper(get(), get(), get()) }

    factory<GameApi> {
        val retrofit by inject<Retrofit>()
        retrofit.create(GameApi::class.java)
    }

    factory { GameDealMapper(get()) }
}
