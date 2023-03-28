package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.ViewModel
import kotlinx.coroutines.flow.*

class LatestDealsViewModel(
    private val latestDealsOrchestrator: LatestDealsOrchestrator,
    private val latestDealsUiModelMapper: LatestDealsUiModelMapper,
) : ViewModel<LatestDealsUiState, LatestDealsInputEvent>(initialState = LatestDealsUiState.Idle) {

    override fun onInputEvent(inputEvent: LatestDealsInputEvent) {
        when (inputEvent) {
            LatestDealsInputEvent.Load -> loadLatestDeals()
        }
    }

    private fun loadLatestDeals() {
        latestDealsOrchestrator.findLatestDeals()
            .map {
                println(it)
                return@map latestDealsUiModelMapper(it)
            }
            .catch { mutableUiState.value = LatestDealsUiState.Error }
            .onStart { mutableUiState.value = LatestDealsUiState.Loading }
            .onEach {
                println(it)
                mutableUiState.value = LatestDealsUiState.Loaded(it)
            }
            .launchIn(viewModelScope)
    }
}
