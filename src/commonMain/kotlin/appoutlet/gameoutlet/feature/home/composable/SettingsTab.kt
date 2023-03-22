package appoutlet.gameoutlet.feature.home.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.settings.SettingsView
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object SettingsTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 3u,
            title = i18n.tr("Settings"),
            icon = rememberVectorPainter(Icons.Outlined.Settings)
        )

    @Composable
    override fun Content() {
        Navigator(SettingsView())
    }
}
