package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.api.DealApi
import appoutlet.gameoutlet.repository.deals.api.DealResponse
import appoutlet.gameoutlet.repository.deals.api.GameApi
import appoutlet.gameoutlet.repository.deals.api.GameResponse
import appoutlet.gameoutlet.repository.deals.api.GameSearchResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DealRepositoryTest : UnitTest<DealRepository>() {
    private val mockDealApi = mockk<DealApi>()
    private val mockDealMapper = mockk<DealMapper>()
    private val mockGameApi = mockk<GameApi>()
    private val mockGameDealMapper = mockk<GameDealMapper>()
    private val mockGameMapper = mockk<GameMapper>()

    override fun buildSut() = DealRepository(
        dealApi = mockDealApi,
        dealMapper = mockDealMapper,
        gameApi = mockGameApi,
        gameDealMapper = mockGameDealMapper,
        gameMapper = mockGameMapper,
    )

    @Test
    fun `should find latest deals`() = runTest {
        val fixtureResponses = fixture<List<DealResponse>>()
        val fixtureDeals = fixture<List<Deal>>()

        coEvery { mockDealApi.findLatestDeals() } returns fixtureResponses
        coEvery { mockDealMapper.invoke(fixtureResponses) } returns fixtureDeals

        val actual = sut.findLatestDeals()

        assertThat(actual).isEqualTo(fixtureDeals)
    }

    @Test
    fun `should find deals by game`() = runTest {
        val fixtureGame = fixture<Game>()
        val fixtureGameResponse = fixture<GameResponse>()
        val fixtureDeals = fixture<List<Deal>>()

        coEvery { mockGameApi.findById(fixtureGame.id) } returns fixtureGameResponse
        fixtureGameResponse.deals.forEachIndexed { index, gameDealResponse ->
            every { mockGameDealMapper.invoke(fixtureGame, gameDealResponse) } returns fixtureDeals[index]
        }

        val actual = sut.findDealsByGame(fixtureGame)

        assertThat(actual).isEqualTo(fixtureDeals)
    }

    @Test
    fun `should find games by title`() = runTest {
        val fixtureTitle = fixture<String>()
        val fixtureGameResponses = fixture<List<GameSearchResponse>>()
        val fixtureGames = fixture<List<Game>>()

        coEvery { mockGameApi.findByTitle(fixtureTitle) } returns fixtureGameResponses
        fixtureGameResponses.forEachIndexed { index, gameSearchResponse ->
            every { mockGameMapper.invoke(gameSearchResponse) } returns fixtureGames[index]
        }

        val actual = sut.findGamesByTitle(fixtureTitle)

        assertThat(actual).isEqualTo(fixtureGames)
    }

    @Test
    fun `should find games by store`() = runTest {
        val fixtureStore = fixture<Store>()
        val fixtureDealsResponse = fixture<List<DealResponse>>()
        val fixtureDealsDomain = fixture<List<Deal>>()

        coEvery { mockDealApi.findDealsByStore(fixtureStore.id) } returns fixtureDealsResponse
        coEvery { mockDealMapper.invoke(fixtureDealsResponse) } returns fixtureDealsDomain

        val actual = sut.findDealsByStore(fixtureStore)

        assertThat(actual).isEqualTo(fixtureDealsDomain)
    }
}
