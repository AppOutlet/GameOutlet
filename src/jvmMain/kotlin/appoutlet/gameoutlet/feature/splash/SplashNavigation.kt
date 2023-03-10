package appoutlet.gameoutlet.feature.splash

interface SplashNavigation {
    fun getScreen(): SplashScreen
}

class DefaultSplashNavigation : SplashNavigation {
    override fun getScreen() = SplashScreen()
}
