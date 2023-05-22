package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.feature.common.UiState

sealed interface WishlistUiState : UiState {
    object Idle : WishlistUiState
    object Loading : WishlistUiState
    object Error : WishlistUiState
    data class Loaded(val list: List<WishlistGame>) : WishlistUiState
}

data class WishlistGame(
    val id: Long,
    val title: String,
    val image: String
)