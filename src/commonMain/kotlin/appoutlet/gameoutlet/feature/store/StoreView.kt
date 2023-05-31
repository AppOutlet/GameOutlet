package appoutlet.gameoutlet.feature.store

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.store.composable.StoreContent
import org.koin.core.component.inject

class StoreView(private val store: Store) : View<StoreUiState, StoreInputEvent>() {
    override val viewModel by inject<StoreViewModel>()

    @Composable
    override fun ViewContent(uiState: StoreUiState, onInputEvent: (StoreInputEvent) -> Unit) {
        StoreContent(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            onInputEvent = onInputEvent
        )
    }

    class Provider {
        fun getStoreView(store: Store) = StoreView(store)
    }
}
