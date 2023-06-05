package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.feature.game.GameNavArgs
import appoutlet.gameoutlet.feature.game.GameViewProvider
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class StoreViewModel(
    private val storeOrchestrator: StoreOrchestrator,
    private val storeViewDataMapper: StoreViewDataMapper,
    private val gameViewProvider: GameViewProvider,
) : ViewModel<StoreUiState, StoreInputEvent>(initialState = StoreUiState.Idle) {
    override fun onInputEvent(inputEvent: StoreInputEvent) {
        when (inputEvent) {
            is StoreInputEvent.Load -> loadStore(inputEvent.store)
            is StoreInputEvent.SelectDeal -> goToGame(inputEvent.gameNavArgs)
            StoreInputEvent.NavigateBack -> navigator.pop()
        }
    }

    private fun loadStore(store: Store) {
        storeOrchestrator.loadStore(store)
            .map { storeViewDataMapper(it) }
            .onEach { mutableUiState.value = StoreUiState.Loaded(it) }
            .onStart { mutableUiState.value = StoreUiState.Loading }
            .catch { mutableUiState.value = StoreUiState.Error }
            .launchIn(viewModelScope)
    }

    private fun goToGame(gameNavArgs: GameNavArgs) {
        val gameView = gameViewProvider.getGameView(gameNavArgs)
        navigator.push(gameView)
    }
}
