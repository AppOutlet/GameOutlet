package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.feature.common.UiState

sealed interface StoreListUiState : UiState {
    object Idle : StoreListUiState
    object Loading : StoreListUiState
    object Error : StoreListUiState
    data class Loaded(val stores: List<StoreUiModel>) : StoreListUiState
}

data class StoreUiModel(
    val icon: String?,
    val name: String,
    val inputEvent: StoreListInputEvent
)