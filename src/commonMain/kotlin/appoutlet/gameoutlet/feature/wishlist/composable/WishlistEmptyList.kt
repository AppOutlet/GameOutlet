package appoutlet.gameoutlet.feature.wishlist.composable

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.wishlist.WishlistInputEvent

@Suppress("MaxLineLength")
@Composable
fun WishlistEmptyList(onInputEvent: (WishlistInputEvent) -> Unit, modifier: Modifier = Modifier) {
    Error(
        modifier = modifier.fillMaxHeight().widthIn(max = 500.dp),
        title = i18n.tr("You didn't save any game yet"),
        message = i18n.tr(
            "Your saved games will appear here. See the latest deals or search by your favourite game and then click on the favorite button."
        ),
        buttonText = i18n.tr("Take me to search screen"),
        onTryAgain = { onInputEvent(WishlistInputEvent.GoToSearch) },
    )
}
