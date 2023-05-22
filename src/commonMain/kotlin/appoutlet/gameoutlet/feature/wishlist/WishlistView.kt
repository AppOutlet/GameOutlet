package appoutlet.gameoutlet.feature.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.common.composable.Loading
import appoutlet.gameoutlet.feature.wishlist.composable.WishlistContent
import org.koin.core.component.inject

class WishlistView : View<WishlistUiState, WishlistInputEvent>() {
    override val viewModel by inject<WishlistViewModel>()

    @Composable
    override fun ViewContent(uiState: WishlistUiState, onInputEvent: (WishlistInputEvent) -> Unit) {
        when (uiState) {
            WishlistUiState.Idle -> onInputEvent(WishlistInputEvent.Load)

            WishlistUiState.Error -> Error(
                modifier = Modifier.fillMaxSize().semantics { testTag = "errorIndicator" },
                message = i18n.tr("We were unable to get the saved games"),
                onTryAgain = { onInputEvent(WishlistInputEvent.Load) },
            )

            WishlistUiState.Loading -> Loading(text = i18n.tr("Fetching the saved games for you"))

            is WishlistUiState.Loaded -> WishlistContent(
                uiState = uiState,
                onInputEvent = onInputEvent
            )
        }
    }
}
