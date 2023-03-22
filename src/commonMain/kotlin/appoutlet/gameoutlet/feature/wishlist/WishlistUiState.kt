package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.feature.common.UiState

sealed interface WishlistUiState : UiState {
    object Idle : WishlistUiState
}
