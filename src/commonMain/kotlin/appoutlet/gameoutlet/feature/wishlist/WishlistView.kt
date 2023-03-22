package appoutlet.gameoutlet.feature.wishlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.inject

class WishlistView : View<WishlistUiState, WishlistInputEvent>() {
    override val viewModel by inject<WishlistViewModel>()
    override val initialState = WishlistUiState.Idle

    @Composable
    override fun ViewContent(uiState: WishlistUiState, onInputEvent: (WishlistInputEvent) -> Unit) {
        Text(i18n.tr("Wishlist"))
    }
}
