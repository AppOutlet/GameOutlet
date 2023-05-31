package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.feature.common.ViewModel

class StoreViewModel : ViewModel<StoreUiState, StoreInputEvent>(initialState = StoreUiState.Idle) {
    override fun onInputEvent(inputEvent: StoreInputEvent) {
        when (inputEvent) {
            StoreInputEvent.Load -> TODO()
            is StoreInputEvent.SelectDeal -> TODO()
            StoreInputEvent.NavigateBack -> navigator.pop()
        }
    }
}
