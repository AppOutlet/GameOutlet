package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.feature.game.GameNavArgs
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.feature.home.composable.GameSearchTab
import appoutlet.gameoutlet.feature.latestdeals.composable.DealUiModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class LatestDealsViewModel(
    private val latestDealsOrchestrator: LatestDealsOrchestrator,
    private val latestDealsUiModelMapper: LatestDealsUiModelMapper,
    private val gameViewProvider: GameViewProvider,
) : ViewModel<LatestDealsUiState, LatestDealsInputEvent>(initialState = LatestDealsUiState.Idle) {

    override fun onInputEvent(inputEvent: LatestDealsInputEvent) {
        when (inputEvent) {
            LatestDealsInputEvent.Load -> loadLatestDeals()
            LatestDealsInputEvent.ToSearch -> goToSearchScreen()
            is LatestDealsInputEvent.DealClicked -> onDealClicked(inputEvent.deal)
        }
    }

    private fun loadLatestDeals() {
        latestDealsOrchestrator.findLatestDeals()
            .map { latestDealsUiModelMapper(it) }
            .catch {
                Napier.e(message = "Error when loading latest deals", throwable = it)
                mutableUiState.value = LatestDealsUiState.Error
            }
            .onStart { mutableUiState.value = LatestDealsUiState.Loading }
            .onEach { mutableUiState.value = LatestDealsUiState.Loaded(it) }
            .launchIn(viewModelScope)
    }

    private fun onDealClicked(dealUiModel: DealUiModel) {
        val gameNavArgs = GameNavArgs(
            gameId = dealUiModel.gameId,
            gameTitle = dealUiModel.gameTitle,
            gameImage = dealUiModel.gameImage,
        )

        navigator.push(gameViewProvider.getGameView(gameNavArgs))
    }

    private fun goToSearchScreen() {
        navigator.parent?.replaceAll(GameSearchTab)
    }
}
