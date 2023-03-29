package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.feature.common.ViewModel

class WishlistViewModel : ViewModel<WishlistUiState, WishlistInputEvent>(initialState = WishlistUiState.Idle) {
    override fun onInputEvent(inputEvent: WishlistInputEvent) {
        println(inputEvent)
    }
}
