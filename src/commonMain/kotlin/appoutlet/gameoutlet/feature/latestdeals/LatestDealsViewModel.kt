package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.ViewModel

class LatestDealsViewModel : ViewModel<LatestDealsUiState, LatestDealsInputEvent>() {
    override fun onInputEvent(inputEvent: LatestDealsInputEvent) {
        println(inputEvent)
    }
}
