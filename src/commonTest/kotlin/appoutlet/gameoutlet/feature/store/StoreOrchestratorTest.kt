package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.DealRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class StoreOrchestratorTest : UnitTest<StoreOrchestrator>() {
    private val mockDealRepository = mockk<DealRepository>()

    override fun buildSut() = StoreOrchestrator(mockDealRepository)

    @Test
    fun `should load stores`() = runTest {
        val fixtureStore = fixture<Store>()
        val fixtureDeals = fixture<List<Deal>>()

        coEvery { mockDealRepository.findDealsByStore(fixtureStore) } returns fixtureDeals

        val actual = sut.loadStore(fixtureStore).first()

        assertThat(actual).isEqualTo(fixtureDeals)
    }
}