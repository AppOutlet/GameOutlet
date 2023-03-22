package appoutlet.gameoutlet.feature.home.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.wishlist.WishlistView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object WishlistTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = i18n.tr("Wishlist"),
            icon = rememberVectorPainter(Icons.Outlined.FavoriteBorder)
        )

    @Composable
    override fun Content() {
        Navigator(WishlistView())
    }
}
