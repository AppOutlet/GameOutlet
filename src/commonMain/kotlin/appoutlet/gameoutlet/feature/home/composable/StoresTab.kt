package appoutlet.gameoutlet.feature.home.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Store
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.storelist.StoreListView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object StoresTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 2u,
            title = i18n.tr("Stores"),
            icon = rememberVectorPainter(Icons.Outlined.Store)
        )

    @Composable
    override fun Content() {
        Navigator(StoreListView())
    }
}
