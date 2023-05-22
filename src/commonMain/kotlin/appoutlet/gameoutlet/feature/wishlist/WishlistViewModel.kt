package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.feature.common.ViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class WishlistViewModel(
    private val wishlistOrchestrator: WishlistOrchestrator,
    private val wishlistUiStateMapper: WishlistUiStateMapper,
) : ViewModel<WishlistUiState, WishlistInputEvent>(initialState = WishlistUiState.Idle) {
    override fun onInputEvent(inputEvent: WishlistInputEvent) {
        when (inputEvent) {
            WishlistInputEvent.Load -> loadSavedGames()
        }
    }

    private fun loadSavedGames() {
        wishlistOrchestrator.findAll()
            .map { wishlistUiStateMapper(it) }
            .onStart { mutableUiState.value = WishlistUiState.Loading }
            .catch { mutableUiState.value = WishlistUiState.Error }
            .onEach {
                mutableUiState.value = WishlistUiState.Loaded(it)
            }
            .launchIn(viewModelScope)
    }
}
