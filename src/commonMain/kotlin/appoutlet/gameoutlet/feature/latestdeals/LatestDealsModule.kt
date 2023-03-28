package appoutlet.gameoutlet.feature.latestdeals

import org.koin.dsl.module

val latestDealsModule = module {
    factory { LatestDealsViewModel(get(), get(), get()) }
    factory { LatestDealsOrchestrator(get(), get()) }
    factory { LatestDealsUiModelMapper() }
}
