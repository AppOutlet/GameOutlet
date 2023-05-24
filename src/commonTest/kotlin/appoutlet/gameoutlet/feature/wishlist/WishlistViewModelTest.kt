package appoutlet.gameoutlet.feature.wishlist

import app.cash.sqldelight.Query
import appoutlet.gameoutlet.core.database.GameEntity
import appoutlet.gameoutlet.core.database.GameQueries
import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.feature.game.GameView
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.feature.home.composable.GameSearchTab
import cafe.adriel.voyager.navigator.Navigator
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
internal class WishlistViewModelTest : ViewModelTest<WishlistViewModel>() {
    private val mockWishlistOrchestrator = mockk<WishlistOrchestrator>()
    private val mockWishlistUiStateMapper = mockk<WishlistUiStateMapper>()
    private val mockGameViewProvider = mockk<GameViewProvider>()
    private val mockFindAllQuery = mockk<Query<GameEntity>>(relaxUnitFun = true)
    private val mockGameQueries = mockk<GameQueries> {
        every { findAll() } returns mockFindAllQuery
    }

    override fun buildSut() = WishlistViewModel(
        wishlistOrchestrator = mockWishlistOrchestrator,
        wishlistUiStateMapper = mockWishlistUiStateMapper,
        gameViewProvider = mockGameViewProvider,
        gameQueries = mockGameQueries,
    )

    @Test
    fun `should load games`() {
        val fixtureGames = fixture<List<Game>>()
        val fixtureUiModels = fixture<List<WishlistGameUiModel>>()

        every { mockWishlistOrchestrator.findAll() } returns fixtureGames
        every { mockWishlistUiStateMapper.invoke(fixtureGames) } returns fixtureUiModels

        sut.onInputEvent(WishlistInputEvent.Load)

        assertThat(sut.uiState.value).isEqualTo(WishlistUiState.Loaded(fixtureUiModels))
    }

    @Test
    fun `should navigate to game details`() = runViewModelTest {
        val fixtureUiModel = fixture<WishlistGameUiModel>()
        val mockGameView = mockk<GameView>()

        every { mockGameViewProvider.getGameView(any()) } returns mockGameView

        sut.onInputEvent(WishlistInputEvent.GameClicked(fixtureUiModel))

        verify { mockNavigator.push(mockGameView) }
    }

    @Test
    fun `should navigate to search`() = runViewModelTest {
        val mockNavigatorParent = mockk<Navigator>(relaxUnitFun = true)

        every { mockNavigator.parent } returns mockNavigatorParent

        sut.onInputEvent(WishlistInputEvent.GoToSearch)

        verify { mockNavigatorParent.replaceAll(GameSearchTab) }
    }
}