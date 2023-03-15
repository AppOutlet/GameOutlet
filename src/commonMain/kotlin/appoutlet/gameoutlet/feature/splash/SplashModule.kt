package appoutlet.gameoutlet.feature.splash

import org.koin.dsl.module

val splashModule = module {
    factory {
        SplashViewModel(get())
    }

    factory<SplashNavigation> { DefaultSplashNavigation() }

    factory { SplashOrchestrator(get()) }
}
