package appoutlet.gameoutlet.repository.theme

import org.koin.dsl.module

val themeRepositoryModule = module {
    factory { ThemeRepository(get()) }
}
