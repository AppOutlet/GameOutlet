package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.repository.deals.api.DealApi
import appoutlet.gameoutlet.repository.deals.api.DealResponse
import appoutlet.gameoutlet.repository.deals.api.GameApi
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
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

    override fun buildSut() = DealRepository(
        dealApi = mockDealApi,
        dealMapper = mockDealMapper,
        gameApi = mockGameApi,
        gameDealMapper = mockGameDealMapper
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
}
