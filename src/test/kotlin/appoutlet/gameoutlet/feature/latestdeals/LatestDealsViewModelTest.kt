package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.feature.game.GameNavArgs
import appoutlet.gameoutlet.feature.game.GameView
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.feature.home.composable.GameSearchTab
import appoutlet.gameoutlet.feature.latestdeals.composable.DealUiModel
import cafe.adriel.voyager.navigator.Navigator
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LatestDealsViewModelTest : ViewModelTest<LatestDealsViewModel>() {
    private val mockLatestDealsOrchestrator = mockk<LatestDealsOrchestrator>()
    private val mockLatestDealsUiModelMapper = mockk<LatestDealsUiModelMapper>()
    private val mockGameViewProvider = mockk<GameViewProvider>()

    override fun buildSut() = LatestDealsViewModel(
        latestDealsOrchestrator = mockLatestDealsOrchestrator,
        latestDealsUiModelMapper = mockLatestDealsUiModelMapper,
        gameViewProvider = mockGameViewProvider,
    )

    @Test
    fun `should load latest deals`() = runTest {
        val fixtureLatestDeals = fixture<List<Deal>>()
        val fixtureLatestDealsUiModels = fixture<List<DealUiModel>>()

        every { mockLatestDealsOrchestrator.findLatestDeals() } returns flow {
            delay(3)
            emit(fixtureLatestDeals)
        }

        every { mockLatestDealsUiModelMapper.invoke(fixtureLatestDeals) } returns fixtureLatestDealsUiModels

        sut.init(this, mockNavigator)

        assertThat(sut.uiState.value).isEqualTo(LatestDealsUiState.Idle)

        sut.onInputEvent(LatestDealsInputEvent.Load)

        advanceTimeBy(1)

        assertThat(sut.uiState.value).isEqualTo(LatestDealsUiState.Loading)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(LatestDealsUiState.Loaded(fixtureLatestDealsUiModels))
    }

    @Suppress("TooGenericExceptionThrown")
    @Test
    fun `should load latest deals - error`() = runTest {
        val fixtureLatestDeals = fixture<List<Deal>>()
        val fixtureLatestDealsUiModels = fixture<List<DealUiModel>>()

        every { mockLatestDealsOrchestrator.findLatestDeals() } returns flow {
            delay(3)
            throw RuntimeException()
        }

        every { mockLatestDealsUiModelMapper.invoke(fixtureLatestDeals) } returns fixtureLatestDealsUiModels

        sut.init(this, mockNavigator)

        assertThat(sut.uiState.value).isEqualTo(LatestDealsUiState.Idle)

        sut.onInputEvent(LatestDealsInputEvent.Load)

        advanceTimeBy(1)

        assertThat(sut.uiState.value).isEqualTo(LatestDealsUiState.Loading)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(LatestDealsUiState.Error)
    }

    @Test
    fun `should go to search`() = runViewModelTest {
        val mockParentNavigator = mockk<Navigator>(relaxed = true)

        every { mockNavigator.parent } returns mockParentNavigator

        sut.onInputEvent(LatestDealsInputEvent.ToSearch)

        verify { mockParentNavigator.replaceAll(GameSearchTab) }
    }

    @Test
    fun `should go to deal detail`() = runViewModelTest {
        val fixtureDealUiModel = fixture<DealUiModel>()
        val fixtureGameNavArgs = GameNavArgs(
            gameId = fixtureDealUiModel.gameId,
            gameTitle = fixtureDealUiModel.gameTitle,
            gameImage = fixtureDealUiModel.gameImage,
        )
        val mockGameView = mockk<GameView>()

        every { mockGameViewProvider.getGameView(fixtureGameNavArgs) } returns mockGameView

        sut.onInputEvent(LatestDealsInputEvent.DealClicked(fixtureDealUiModel))

        verify { mockNavigator.push(mockGameView) }
    }
}
