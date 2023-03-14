package appoutlet.gameoutlet.feature.splash

interface SplashNavigation {
    fun getScreen(): SplashView
}

class DefaultSplashNavigation : SplashNavigation {
    override fun getScreen() = SplashView()
}
