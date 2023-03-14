package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.feature.util.ViewModel
import appoutlet.gameoutlet.repository.store.StoreRepository

class SplashViewModel(
    private val storeRepository: StoreRepository,
): ViewModel<SplashUiState, SplashInputEvent>() {

    override fun onInputEvent(inputEvent: SplashInputEvent) {
        when(inputEvent) {
            SplashInputEvent.Load -> loadStores()
        }
    }

    private fun loadStores() {
        println("Load")
    }
}
