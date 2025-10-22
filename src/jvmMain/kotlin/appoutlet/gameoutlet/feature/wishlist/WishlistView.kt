package appoutlet.gameoutlet.feature.wishlist

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.wishlist.composable.WishlistContent
import org.koin.core.component.inject

class WishlistView : View<WishlistUiState, WishlistInputEvent>() {
    override val viewModel by inject<WishlistViewModel>()

    @Composable
    override fun ViewContent(uiState: WishlistUiState, onInputEvent: (WishlistInputEvent) -> Unit) {
        WishlistContent(uiState = uiState, onInputEvent = onInputEvent)
    }
}
