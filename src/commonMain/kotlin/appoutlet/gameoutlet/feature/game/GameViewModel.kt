package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.core.util.DesktopHelper
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.feature.common.ViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import java.awt.Desktop
import java.net.URI

class GameViewModel(
    private val gameOrchestrator: GameOrchestrator,
    private val gameUiModelMapper: GameUiModelMapper,
    private val desktopHelper: DesktopHelper,
) : ViewModel<GameUiState, GameInputEvent>(initialState = GameUiState.Idle) {
    override fun onInputEvent(inputEvent: GameInputEvent) {
        when (inputEvent) {
            GameInputEvent.NavigateBack -> navigator.pop()

            is GameInputEvent.Load -> loadGame(inputEvent.gameNavArgs)

            is GameInputEvent.DealClicked -> {
                openDealLink(inputEvent.deal)
            }
        }
    }

    private fun loadGame(gameNavArgs: GameNavArgs) {
        val game = Game(
            id = gameNavArgs.gameId,
            title = gameNavArgs.gameTitle,
            image = gameNavArgs.gameImage,
        )

        gameOrchestrator.findById(game)
            .onStart { mutableUiState.value = GameUiState.Loading }
            .catch { mutableUiState.value = GameUiState.Error }
            .onEach { deals ->
                val gameUiModel = gameUiModelMapper(game, deals)
                mutableUiState.value = GameUiState.Loaded(gameUiModel = gameUiModel)
            }
            .launchIn(viewModelScope)
    }

    private fun openDealLink(deal: GameDealUiModel) {
        desktopHelper.openLink("https://www.cheapshark.com/redirect?dealID=${deal.id}")
    }
}
