package appoutlet.gameoutlet.feature.storelist

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.storelist.composable.StoreListContent
import org.koin.core.component.inject

class StoreListView : View<StoreListUiState, StoreListInputEvent>() {
    override val viewModel by inject<StoreListViewModel>()

    @Composable
    override fun ViewContent(uiState: StoreListUiState, onInputEvent: (StoreListInputEvent) -> Unit) {
        StoreListContent(uiState = uiState, onInputEvent = onInputEvent)
    }
}
