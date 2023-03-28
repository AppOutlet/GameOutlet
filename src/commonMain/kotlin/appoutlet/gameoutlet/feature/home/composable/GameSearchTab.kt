package appoutlet.gameoutlet.feature.home.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions


object GameSearchTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = i18n.tr("Search"),
            icon = rememberVectorPainter(Icons.Outlined.Search)
        )

    @Composable
    override fun Content() {
        ScreenTitle(i18n.tr("Game search"))
    }
}