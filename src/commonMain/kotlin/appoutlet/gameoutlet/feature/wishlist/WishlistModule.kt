package appoutlet.gameoutlet.feature.wishlist

import org.koin.dsl.module

val wishlistModule = module {
    factory { WishlistViewModel(get(), get(), get()) }
    factory { WishlistOrchestrator(get()) }
    factory { WishlistUiStateMapper() }
}
