package appoutlet.gameoutlet.feature.latestdeals

import org.koin.dsl.module

val latestDealsModule = module {
    factory { LatestDealsViewModel() }
}
