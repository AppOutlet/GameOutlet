package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.feature.game.GameView
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.feature.home.composable.GameSearchTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import kotlinx.coroutines.flow.*

class LatestDealsViewModel(
    private val latestDealsOrchestrator: LatestDealsOrchestrator,
    private val latestDealsUiModelMapper: LatestDealsUiModelMapper,
    private val gameViewProvider: GameViewProvider,
) : ViewModel<LatestDealsUiState, LatestDealsInputEvent>(initialState = LatestDealsUiState.Idle) {

    override fun onInputEvent(inputEvent: LatestDealsInputEvent) {
        when (inputEvent) {
            LatestDealsInputEvent.Load -> loadLatestDeals()
            is LatestDealsInputEvent.DealClicked -> onDealClicked(inputEvent.gameId)
        }
    }

    private fun loadLatestDeals() {
        latestDealsOrchestrator.findLatestDeals()
            .map { latestDealsUiModelMapper(it) }
            .catch { mutableUiState.value = LatestDealsUiState.Error }
            .onStart { mutableUiState.value = LatestDealsUiState.Loading }
            .onEach { mutableUiState.value = LatestDealsUiState.Loaded(it) }
            .launchIn(viewModelScope)
    }

    private fun onDealClicked(gameId: Long) {
        navigator.push(gameViewProvider.getGameView(gameId))
    }
}
