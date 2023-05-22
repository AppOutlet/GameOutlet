package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.feature.game.GameNavArgs
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.feature.home.composable.GameSearchTab
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class WishlistViewModel(
    private val wishlistOrchestrator: WishlistOrchestrator,
    private val wishlistUiStateMapper: WishlistUiStateMapper,
    private val gameViewProvider: GameViewProvider,
) : ViewModel<WishlistUiState, WishlistInputEvent>(initialState = WishlistUiState.Idle) {
    override fun onInputEvent(inputEvent: WishlistInputEvent) {
        when (inputEvent) {
            WishlistInputEvent.Load -> loadSavedGames()
            is WishlistInputEvent.GameClicked -> navigateToGameDetail(inputEvent.game)
            WishlistInputEvent.GoToSearch -> {
                navigator.parent?.replaceAll(GameSearchTab)
            }
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

    private fun navigateToGameDetail(gameUiModel: WishlistGameUiModel) {
        val gameNavArgs = GameNavArgs(
            gameId = gameUiModel.id,
            gameTitle = gameUiModel.title,
            gameImage = gameUiModel.image,
        )

        navigator.push(gameViewProvider.getGameView(gameNavArgs))
    }
}
