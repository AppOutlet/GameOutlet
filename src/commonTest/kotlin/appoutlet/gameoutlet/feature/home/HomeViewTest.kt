package appoutlet.gameoutlet.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.about.AboutUiState
import appoutlet.gameoutlet.feature.about.AboutViewModel
import appoutlet.gameoutlet.feature.gamesearch.GameSearchUiState
import appoutlet.gameoutlet.feature.gamesearch.GameSearchViewModel
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsUiState
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsViewModel
import appoutlet.gameoutlet.feature.settings.SettingsUiState
import appoutlet.gameoutlet.feature.settings.SettingsViewModel
import appoutlet.gameoutlet.feature.storelist.StoreListUiState
import appoutlet.gameoutlet.feature.storelist.StoreListViewModel
import appoutlet.gameoutlet.feature.wishlist.WishlistUiState
import appoutlet.gameoutlet.feature.wishlist.WishlistViewModel
import cafe.adriel.voyager.navigator.Navigator
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test
import org.koin.dsl.module

class HomeViewTest : UiTest() {
    private val mockLatestDealsViewModel = mockk<LatestDealsViewModel>(relaxUnitFun = true) {
        every { uiState } returns MutableStateFlow(LatestDealsUiState.Idle)
    }

    private val mockWishlistViewModel = mockk<WishlistViewModel>(relaxUnitFun = true) {
        every { uiState } returns MutableStateFlow(fixture<WishlistUiState.Loaded>())
    }

    private val mockSettingsViewModel = mockk<SettingsViewModel>(relaxUnitFun = true) {
        every { uiState } returns MutableStateFlow(SettingsUiState.Idle)
    }

    private val mockStoreListViewModel = mockk<StoreListViewModel>(relaxUnitFun = true) {
        every { uiState } returns MutableStateFlow(StoreListUiState.Idle)
    }

    private val mockGameSearchViewModel = mockk<GameSearchViewModel>(relaxUnitFun = true) {
        every { uiState } returns MutableStateFlow(GameSearchUiState.Idle(""))
    }

    private val mockAboutViewModel = mockk<AboutViewModel>(relaxUnitFun = true) {
        every { uiState } returns MutableStateFlow(fixture<AboutUiState.Loaded>())
    }

    override val koinTestModule = module {
        single { mockLatestDealsViewModel }
        single { mockWishlistViewModel }
        single { mockSettingsViewModel }
        single { mockStoreListViewModel }
        single { mockGameSearchViewModel }
        single { mockAboutViewModel }
    }

    @Test
    fun `should start with Latest deals selected`() {
        every { mockLatestDealsViewModel.uiState } returns MutableStateFlow(
            LatestDealsUiState.Loaded(uiModels = fixture())
        )

        composeTestRule.setContent { HomeView().Content() }

        composeTestRule.onNodeWithTag("latestDealsTab")
            .assertIsSelected()

        composeTestRule.onNodeWithTag("screenTitle")
            .assertTextEquals(i18n.tr("Latest deals"))
    }

    @Test
    fun `should navigate to Wishlist`() {
        composeTestRule.setContent { Navigator(HomeView()) }

        composeTestRule.onNodeWithTag("wishlistTab")
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("screenTitle")
            .assertTextEquals(i18n.tr("Wishlist"))
    }

    @Test
    fun `should navigate to Stores`() {
        composeTestRule.setContent { Navigator(HomeView()) }

        composeTestRule.onNodeWithTag("storesTab")
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("screenTitle")
            .assertTextEquals(i18n.tr("Stores"))
    }

    @Test
    fun `should navigate to Settings`() {
        composeTestRule.setContent { Navigator(HomeView()) }

        composeTestRule.onNodeWithTag("settingsTab")
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("screenTitle")
            .assertTextEquals(i18n.tr("Settings"))
    }

    @Test
    fun `should navigate to game search`() {
        composeTestRule.setContent { Navigator(HomeView()) }

        composeTestRule.onNodeWithTag("gameSearchTab")
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("screenTitle")
            .assertTextEquals(i18n.tr("Search"))
    }

    @Test
    fun `should navigate to About`() {
        composeTestRule.setContent { Navigator(HomeView()) }

        composeTestRule.onNodeWithTag("aboutTab")
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("GameOutlet")
            .assertIsDisplayed()
    }
}
