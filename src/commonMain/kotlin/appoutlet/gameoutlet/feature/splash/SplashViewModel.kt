package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.repository.store.StoreRepository
import name.kropp.kotlinx.gettext.I18n

class SplashViewModel(
    i18n: I18n,
    private val storeRepository: StoreRepository,
) {
    val message = i18n.tr("Powered by App Outlet")
}
