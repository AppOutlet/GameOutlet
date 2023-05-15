package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.DealRepository
import appoutlet.gameoutlet.repository.store.StoreRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameOrchestratorTest : UnitTest<GameOrchestrator>() {
    private val mockStoreRepository = mockk<StoreRepository>()
    private val mockDealRepository = mockk<DealRepository>()

    override fun buildSut() = GameOrchestrator(
        storeRepository = mockStoreRepository,
        dealRepository = mockDealRepository,
    )

    @Test
    fun `should fund game by id`() = runTest {
        val fixtureGame = fixture<Game>()
        val fixtureDeals = fixture<List<Deal>>()
        val fixtureStore = fixture<Store>()

        coEvery { mockDealRepository.findDealsByGame(fixtureGame) } returns fixtureDeals
        fixtureDeals.forEach {
            every { mockStoreRepository.findById(it.store.id) } returns fixtureStore
        }

        val actualDeals = sut.findDealsByGame(fixtureGame).first()

        actualDeals.forEachIndexed { index, deal ->
            assertThat(deal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(deal.store).isEqualTo(fixtureStore)
        }
    }
}
