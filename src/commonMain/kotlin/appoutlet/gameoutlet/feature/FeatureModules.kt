package appoutlet.gameoutlet.feature

import appoutlet.gameoutlet.feature.about.aboutModule
import appoutlet.gameoutlet.feature.game.gameModule
import appoutlet.gameoutlet.feature.gamesearch.gameSearchModule
import appoutlet.gameoutlet.feature.home.homeModule
import appoutlet.gameoutlet.feature.latestdeals.latestDealsModule
import appoutlet.gameoutlet.feature.settings.settingsModule
import appoutlet.gameoutlet.feature.splash.splashModule
import appoutlet.gameoutlet.feature.store.storeModule
import appoutlet.gameoutlet.feature.storelist.storeListModule
import appoutlet.gameoutlet.feature.wishlist.wishlistModule

val featureModules = arrayOf(
    splashModule,
    homeModule,
    latestDealsModule,
    wishlistModule,
    storeListModule,
    settingsModule,
    gameModule,
    gameSearchModule,
    storeModule,
    aboutModule,
)
