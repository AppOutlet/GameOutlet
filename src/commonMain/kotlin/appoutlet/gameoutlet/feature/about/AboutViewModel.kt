package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.feature.common.ViewModel

class AboutViewModel : ViewModel<AboutUiState, AboutInputEvent>(initialState = AboutUiState.Idle) {
    override fun onInputEvent(inputEvent: AboutInputEvent) {
        when (inputEvent) {
            AboutInputEvent.GoToContribution -> {}
        }
    }
}
