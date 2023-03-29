package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.feature.common.ViewModel

class StoreListViewModel : ViewModel<StoreListUiState, StoreListInputEvent>(initialState = StoreListUiState.Idle) {
    override fun onInputEvent(inputEvent: StoreListInputEvent) {
        println(inputEvent)
    }
}
