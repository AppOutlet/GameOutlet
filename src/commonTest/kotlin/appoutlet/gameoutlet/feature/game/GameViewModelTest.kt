package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.core.util.DesktopHelper
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest : ViewModelTest<GameViewModel>() {
    private val mockGameOrchestrator = mockk<GameOrchestrator>(relaxUnitFun = true)
    private val mockGameUiModelMapper = mockk<GameUiModelMapper>()
    private val mockDesktopHelper = mockk<DesktopHelper>(relaxUnitFun = true)

    override fun buildSut() = GameViewModel(
        gameOrchestrator = mockGameOrchestrator,
        gameUiModelMapper = mockGameUiModelMapper,
        desktopHelper = mockDesktopHelper,
    )

    @Test
    fun `should navigate back`() = runViewModelTest {
        sut.onInputEvent(GameInputEvent.NavigateBack)

        verify { mockNavigator.pop() }
    }

    @Test
    fun `should load game`() = runViewModelTest {
        val fixtureGameNavArgs = fixture<GameNavArgs>()
        val fixtureGame = Game(
            id = fixtureGameNavArgs.gameId,
            title = fixtureGameNavArgs.gameTitle,
            image = fixtureGameNavArgs.gameImage,
        )
        val fixtureDeals = fixture<List<Deal>>()
        val fixtureGameUiModel = fixture<GameUiModel>()

        every { mockGameOrchestrator.findDealsByGame(fixtureGame) } returns flow {
            delay(3)
            emit(fixtureDeals)
        }
        coEvery { mockGameUiModelMapper.invoke(fixtureGame, fixtureDeals, false, false) } returns fixtureGameUiModel
        every { mockGameOrchestrator.checkIfGameIsSaved(fixtureGame) } returns false

        assertThat(sut.uiState.value).isEqualTo(GameUiState.Idle)

        sut.onInputEvent(GameInputEvent.Load(fixtureGameNavArgs))

        advanceTimeBy(1)

        assertThat(sut.uiState.value).isEqualTo(GameUiState.Loading)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(GameUiState.Loaded(fixtureGameUiModel))
    }

    @Test
    fun `should open a link when the deals is clicked`() = runViewModelTest {
        val fixtureGameDealUiModel = fixture<GameDealUiModel>()

        sut.onInputEvent(GameInputEvent.DealClicked(fixtureGameDealUiModel))

        verify { mockDesktopHelper.openLink("https://www.cheapshark.com/redirect?dealID=${fixtureGameDealUiModel.id}") }
    }

    @Test
    fun `should save game`() = runViewModelTest {
        val fixtureEvent = fixture<GameInputEvent.SaveGame>()

        every { mockGameOrchestrator.checkIfGameIsSaved(fixtureEvent.game) } returns false

        sut.onInputEvent(fixtureEvent)

        verify { mockGameOrchestrator.save(fixtureEvent.game) }
    }

    @Test
    fun `should remove game`() = runViewModelTest {
        val fixtureEvent = fixture<GameInputEvent.RemoveGameFromFavorites>()

        every { mockGameOrchestrator.checkIfGameIsSaved(fixtureEvent.game) } returns false

        sut.onInputEvent(fixtureEvent)

        verify { mockGameOrchestrator.removeGame(fixtureEvent.game) }
    }
}
