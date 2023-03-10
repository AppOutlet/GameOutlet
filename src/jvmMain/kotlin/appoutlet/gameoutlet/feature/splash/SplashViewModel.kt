package appoutlet.gameoutlet.feature.splash

import name.kropp.kotlinx.gettext.I18n

class SplashViewModel(
    i18n: I18n
) {
    val message = i18n.tr("Powered by App Outlet")
}
