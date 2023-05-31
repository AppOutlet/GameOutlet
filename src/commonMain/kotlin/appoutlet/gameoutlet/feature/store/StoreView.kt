package appoutlet.gameoutlet.feature.store

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import org.koin.core.component.inject

class StoreView : View<StoreUiState, StoreInputEvent>() {
    override val viewModel by inject<StoreViewModel>()

    @Composable
    override fun ViewContent(uiState: StoreUiState, onInputEvent: (StoreInputEvent) -> Unit) {
        ScreenTitle(text = i18n.tr("Store"))
    }

    class Provider {
        fun getStoreView() = StoreView()
    }
}