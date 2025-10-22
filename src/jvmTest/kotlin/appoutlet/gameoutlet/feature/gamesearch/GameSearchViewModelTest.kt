package appoutlet.gameoutlet.feature.gamesearch

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.feature.game.GameNavArgs
import appoutlet.gameoutlet.feature.game.GameView
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.repository.deals.DealRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameSearchViewModelTest : ViewModelTest<GameSearchViewModel>() {
    private val mockDealRepository = mockk<DealRepository>()
    private val mockGameSearchUiModelMapper = mockk<GameSearchUiModelMapper>()
    private val mockGameViewProvider = mockk<GameViewProvider>()

    override fun buildSut() = GameSearchViewModel(
        dealsRepository = mockDealRepository,
        gameSearchUiModelMapper = mockGameSearchUiModelMapper,
        gameViewProvider = mockGameViewProvider,
    )

    @Test
    fun `should search`() = runViewModelTest {
        val fixtureSearchTerm = fixture<String>()
        val fixtureGames = fixture<List<Game>>()
        val fixtureUiModels = fixture<List<GameSearchUiModel>>()

        coEvery { mockDealRepository.findGamesByTitle(fixtureSearchTerm) } returns fixtureGames
        fixtureGames.forEachIndexed { index, game ->
            every { mockGameSearchUiModelMapper.invoke(game) } returns fixtureUiModels[index]
        }

        assertThat(sut.uiState.value).isEqualTo(GameSearchUiState.Idle(""))

        sut.onInputEvent(GameSearchInputEvent.Search(fixtureSearchTerm))

        advanceTimeBy(SEARCH_DEBOUNCE_TIME.inWholeMilliseconds + 1)

        assertThat(sut.uiState.value).isEqualTo(
            GameSearchUiState.Loaded(
                searchTerm = fixtureSearchTerm,
                games = fixtureUiModels
            )
        )

        sut.searchJob.cancel()
    }

    @Test
    fun `should search - error`() = runViewModelTest {
        val fixtureSearchTerm = fixture<String>()

        coEvery { mockDealRepository.findGamesByTitle(fixtureSearchTerm) } throws RuntimeException()

        assertThat(sut.uiState.value).isEqualTo(GameSearchUiState.Idle(""))

        sut.onInputEvent(GameSearchInputEvent.Search(fixtureSearchTerm))

        advanceTimeBy(SEARCH_DEBOUNCE_TIME.inWholeMilliseconds + 1)

        assertThat(sut.uiState.value).isEqualTo(
            GameSearchUiState.Error(
                searchTerm = fixtureSearchTerm,
                games = emptyList()
            )
        )

        sut.searchJob.cancel()
    }

    @Test
    fun `should navigate when game is clicked`() = runViewModelTest {
        val fixtureUiModel = fixture<GameSearchUiModel>()
        val fixtureNavArgs = GameNavArgs(
            gameId = fixtureUiModel.id,
            gameTitle = fixtureUiModel.title,
            gameImage = fixtureUiModel.image
        )
        val mockGameView = mockk<GameView>()

        every { mockGameViewProvider.getGameView(fixtureNavArgs) } returns mockGameView

        sut.onInputEvent(GameSearchInputEvent.GameClicked(fixtureUiModel))

        verify { mockNavigator.push(mockGameView) }

        sut.searchJob.cancel()
    }
}
