package appoutlet.gameoutlet.feature.wishlist

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.inject

class WishlistView : View<WishlistUiState, WishlistInputEvent>() {
    override val viewModel by inject<WishlistViewModel>()
    @Composable
    override fun ViewContent(uiState: WishlistUiState, onInputEvent: (WishlistInputEvent) -> Unit) {
        Text(
            modifier = Modifier.semantics { testTag = "screenTitle" },
            text = i18n.tr("Wishlist"),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
