package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.feature.home.HomeViewProvider
import appoutlet.gameoutlet.feature.util.ViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class SplashViewModel(
    private val splashOrchestrator: SplashOrchestrator,
    private val homeViewProvider: HomeViewProvider,
) : ViewModel<SplashUiState, SplashInputEvent>() {

    override fun onInputEvent(inputEvent: SplashInputEvent) {
        when (inputEvent) {
            SplashInputEvent.Load -> loadStores()
        }
    }

    private fun loadStores() {
        splashOrchestrator.synchronizeStoreData()
            .onStart {
                mutableUiState.value = SplashUiState.Loading
            }
            .onEach {
                navigator.replaceAll(homeViewProvider.getView())
            }
            .catch {
                mutableUiState.value = SplashUiState.Error
            }
            .launchIn(viewModelScope)
    }
}
