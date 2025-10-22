package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface WishlistInputEvent : InputEvent {
    object Load : WishlistInputEvent
    data class GameClicked(
        val game: WishlistGameUiModel,
    ) : WishlistInputEvent

    object GoToSearch : WishlistInputEvent
}
