package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.repository.store.StoreRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch

class StoreListViewModel(
    private val storeRepository: StoreRepository,
    private val storeListUiModelMapper: StoreListUiModelMapper
) : ViewModel<StoreListUiState, StoreListInputEvent>(initialState = StoreListUiState.Idle) {
    override fun onInputEvent(inputEvent: StoreListInputEvent) {
        when (inputEvent) {
            StoreListInputEvent.Load -> loadStores()
            is StoreListInputEvent.SelectStore -> TODO()
        }
    }

    private fun loadStores() {
        viewModelScope.launch {
            try {
                mutableUiState.value = StoreListUiState.Loading

                val storesEntity = storeRepository.findAll()
                val uiModels = storeListUiModelMapper(storesEntity)

                mutableUiState.value = StoreListUiState.Loaded(uiModels)
            } catch (throwable: Throwable) {
                Napier.e("Error getting the stores list", throwable)
                mutableUiState.value = StoreListUiState.Error
            }
        }
    }
}
