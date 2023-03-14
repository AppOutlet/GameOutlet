package appoutlet.gameoutlet.feature.splash

import org.koin.dsl.module

val splashModule = module {
    factory {
        SplashViewModel(storeRepository = get())
    }

    factory<SplashNavigation> { DefaultSplashNavigation() }
}
