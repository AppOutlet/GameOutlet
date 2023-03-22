package appoutlet.gameoutlet.feature.home.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object LatestDealsTab : Tab {
    override val options:TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = i18n.tr("Latest deals"),
            icon = rememberVectorPainter(Icons.Outlined.ShoppingBasket)
        )

    @Composable
    override fun Content() {
        Navigator(LatestDealsView())
    }
}
