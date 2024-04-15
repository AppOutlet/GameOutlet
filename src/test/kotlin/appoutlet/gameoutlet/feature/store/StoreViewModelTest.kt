package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.game.GameView
import appoutlet.gameoutlet.feature.game.GameViewProvider
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StoreViewModelTest : ViewModelTest<StoreViewModel>() {
    private val mockStoreOrchestrator = mockk<StoreOrchestrator>()
    private val mockStoreViewDataMapper = mockk<StoreViewDataMapper>()
    private val mockGameViewProvider = mockk<GameViewProvider>()

    override fun buildSut() = StoreViewModel(
        storeOrchestrator = mockStoreOrchestrator,
        storeViewDataMapper = mockStoreViewDataMapper,
        gameViewProvider = mockGameViewProvider,
    )

    @Test
    fun `should load store`() = runViewModelTest {
        val fixtureStore = fixture<Store>()
        val fixtureDeals = fixture<List<Deal>>()
        val fixtureViewData = fixture<StoreViewData>()

        every { mockStoreOrchestrator.loadStore(fixtureStore) } returns flowOf(fixtureDeals)
        every { mockStoreViewDataMapper.invoke(fixtureDeals) } returns fixtureViewData

        sut.onInputEvent(StoreInputEvent.Load(fixtureStore))

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(StoreUiState.Loaded(fixtureViewData))
    }

    @Test
    fun `should select deal`() = runViewModelTest {
        val fixtureInputEvent = fixture<StoreInputEvent.SelectDeal>()
        val mockGameView = mockk<GameView>()

        every { mockGameViewProvider.getGameView(fixtureInputEvent.gameNavArgs) } returns mockGameView

        sut.onInputEvent(fixtureInputEvent)

        verify { mockNavigator.push(mockGameView) }
    }

    @Test
    fun `should navigate back`() = runViewModelTest {
        sut.onInputEvent(StoreInputEvent.NavigateBack)

        verify { mockNavigator.pop() }
    }
}
