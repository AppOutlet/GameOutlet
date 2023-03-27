package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.repository.deals.api.DealApi
import org.koin.dsl.module
import retrofit2.Retrofit

val dealsRepositoryModule = module {
    factory { DealsRepository(get(), get()) }

    factory<DealApi> {
        val retrofit by inject<Retrofit>()
        retrofit.create(DealApi::class.java)
    }
}