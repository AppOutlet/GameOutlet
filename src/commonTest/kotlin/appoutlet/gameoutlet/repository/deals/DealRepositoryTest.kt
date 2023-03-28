package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.repository.deals.api.DealApi
import appoutlet.gameoutlet.repository.deals.api.DealResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class DealRepositoryTest : UnitTest<DealRepository>() {
    private val mockDealApi = mockk<DealApi>()
    private val mockDealMapper = mockk<DealMapper>()
    override fun buildSut() = DealRepository(
        dealApi = mockDealApi,
        dealMapper = mockDealMapper,
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