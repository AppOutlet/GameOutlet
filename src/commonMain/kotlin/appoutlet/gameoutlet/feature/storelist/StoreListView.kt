package appoutlet.gameoutlet.feature.storelist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.inject

class StoreListView : View<StoreListUiState, StoreListInputEvent>() {
    override val viewModel by inject<StoreListViewModel>()
    override val initialState = StoreListUiState.Idle

    @Composable
    override fun ViewContent(uiState: StoreListUiState, onInputEvent: (StoreListInputEvent) -> Unit) {
        Text(i18n.tr("Stores"))
    }
}
