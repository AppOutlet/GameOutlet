package gameoutlet.core.network

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val CHEAP_SHARK_BASE_URL = "https://www.cheapshark.com/api/1.0/"

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(CHEAP_SHARK_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {  }
}
