package appoutlet.gameoutlet.feature.home

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.home.composable.LatestDealsTab
import appoutlet.gameoutlet.feature.util.View
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.koin.core.component.inject

class HomeView : View<HomeUiState, HomeInputEvent>() {
    override val viewModel by inject<HomeViewModel>()
    override val initialState = HomeUiState.Idle

    @Composable
    override fun ViewContent(uiState: HomeUiState, onInputEvent: (HomeInputEvent) -> Unit) {
        HomeViewContent(uiState, onInputEvent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeViewContent(uiState: HomeUiState, onInputEvent: (HomeInputEvent) -> Unit) {
    TabNavigator(LatestDealsTab) {
        PermanentNavigationDrawer(drawerContent = {
            PermanentDrawerSheet(
                modifier = Modifier.width(250.dp).verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                DrawerNavigationItem(LatestDealsTab)
            }
        }) {
            CurrentTab()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.verySmall))

    NavigationDrawerItem(
        icon = { tab.options.icon?.let { painter -> Icon(painter, contentDescription = null) } },
        label = { Text(tab.options.title) },
        selected = tab.key == tabNavigator.current.key,
        onClick = { tabNavigator.current = tab },
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
    )
}
