package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.store.StoreRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SplashOrchestratorTest : UnitTest<SplashOrchestrator>() {
    private val mockStoreRepository = mockk<StoreRepository>()

    override fun buildSut() = SplashOrchestrator(storeRepository = mockStoreRepository)

    @Test
    fun `should synchronize stores`() = runTest {
        val storeListFixture = fixture<List<Store>>()

        coEvery { mockStoreRepository.findAll() } returns storeListFixture

        val actual = sut.synchronizeStoreData().first()

        assertThat(actual).isEqualTo(Unit)

        coVerify(exactly = 1) { mockStoreRepository.findAll() }
    }
}
