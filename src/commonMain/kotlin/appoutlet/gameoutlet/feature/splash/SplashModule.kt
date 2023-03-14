package appoutlet.gameoutlet.feature.splash

import org.koin.dsl.module

val splashModule = module {
    factory {
        SplashViewModel(
            i18n = get(),
            storeRepository = get()
        )
    }

    factory<SplashNavigation> { DefaultSplashNavigation() }
}
