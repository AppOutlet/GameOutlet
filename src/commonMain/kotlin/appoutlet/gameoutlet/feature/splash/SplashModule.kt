package appoutlet.gameoutlet.feature.splash

import org.koin.dsl.module

val splashModule = module {
    factory {
        SplashViewModel(get(), get())
    }

    factory { SplashOrchestrator(get()) }
}
