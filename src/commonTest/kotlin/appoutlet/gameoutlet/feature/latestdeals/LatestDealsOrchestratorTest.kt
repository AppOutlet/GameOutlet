package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.DealRepository
import appoutlet.gameoutlet.repository.store.StoreRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class LatestDealsOrchestratorTest : UnitTest<LatestDealsOrchestrator>() {
    private val mockDealRepository = mockk<DealRepository>()
    private val mockStoreRepository = mockk<StoreRepository>()
    override fun buildSut() = LatestDealsOrchestrator(
        dealRepository = mockDealRepository,
        storeRepository = mockStoreRepository,
    )

    @Test
    fun `should find latest deals`() = runTest {
        val dealsFixture = fixture<List<Deal>>()
        val storesFixture = fixture<List<Store>>()

        coEvery { mockDealRepository.findLatestDeals() } returns dealsFixture
        dealsFixture.forEachIndexed { index, deal ->
            every { mockStoreRepository.findById(deal.store.id) } returns storesFixture[index]
        }

        val actual = sut.findLatestDeals().first()

        actual.forEachIndexed { index, deal ->
            assertThat(deal.id).isEqualTo(dealsFixture[index].id)
            assertThat(deal.store).isEqualTo(storesFixture[index])
        }
    }
}