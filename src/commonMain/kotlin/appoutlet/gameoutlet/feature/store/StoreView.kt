package appoutlet.gameoutlet.feature.store

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import org.koin.core.component.inject

class StoreView(private val store: Store) : View<StoreUiState, StoreInputEvent>() {
    override val viewModel by inject<StoreViewModel>()

    @Composable
    override fun ViewContent(uiState: StoreUiState, onInputEvent: (StoreInputEvent) -> Unit) {
        ScreenTitle(text = i18n.tr(store.name))
    }

    class Provider {
        fun getStoreView(store: Store) = StoreView(store)
    }
}