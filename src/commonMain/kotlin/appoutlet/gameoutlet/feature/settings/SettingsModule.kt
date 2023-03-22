package appoutlet.gameoutlet.feature.settings

import org.koin.dsl.module

val settingsModule = module {
    factory { SettingsViewModel() }
}
