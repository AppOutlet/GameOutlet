package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.feature.common.UiState

sealed interface StoreUiState : UiState {
    object Idle : StoreUiState
    object Loading : StoreUiState
    object Error : StoreUiState
    data class Loaded(val viewData: StoreViewData) : StoreUiState
}
