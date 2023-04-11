package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest : ViewModelTest<GameViewModel>() {
    private val mockGameOrchestrator = mockk<GameOrchestrator>()
    private val mockGameUiModelMapper = mockk<GameUiModelMapper>()

    override fun buildSut() = GameViewModel(
        gameOrchestrator = mockGameOrchestrator,
        gameUiModelMapper = mockGameUiModelMapper,
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

        every { mockGameOrchestrator.findById(fixtureGame) } returns flow {
            delay(3)
            emit(fixtureDeals)
        }
        coEvery { mockGameUiModelMapper.invoke(fixtureGame, fixtureDeals) } returns fixtureGameUiModel

        assertThat(sut.uiState.value).isEqualTo(GameUiState.Idle)

        sut.onInputEvent(GameInputEvent.Load(fixtureGameNavArgs))

        advanceTimeBy(1)

        assertThat(sut.uiState.value).isEqualTo(GameUiState.Loading)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(GameUiState.Loaded(fixtureGameUiModel))
    }
}
