package appoutlet.gameoutlet.feature.home

import appoutlet.gameoutlet.feature.util.ViewModel

class HomeViewModel : ViewModel<HomeUiState, HomeInputEvent>() {
    override fun onInputEvent(inputEvent: HomeInputEvent) {
        when (inputEvent) {
            HomeInputEvent.Load -> Unit // no op
        }
    }
}
