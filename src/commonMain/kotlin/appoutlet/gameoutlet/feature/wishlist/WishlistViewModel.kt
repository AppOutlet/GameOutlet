package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.core.database.GameQueries
import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.feature.game.GameNavArgs
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.feature.home.composable.GameSearchTab

class WishlistViewModel(
    private val wishlistOrchestrator: WishlistOrchestrator,
    private val wishlistUiStateMapper: WishlistUiStateMapper,
    private val gameViewProvider: GameViewProvider,
    private val gameQueries: GameQueries,
) : ViewModel<WishlistUiState, WishlistInputEvent>(initialState = WishlistUiState.Idle) {
    init {
        // remove when sqlite was updated
        gameQueries.findAll().addListener {
            loadSavedGames()
        }
    }

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
        val games = wishlistOrchestrator.findAll()
        val uiModels = wishlistUiStateMapper(games)
        mutableUiState.value = WishlistUiState.Loaded(uiModels)
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
