package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.core.util.TimeProvider
import appoutlet.gameoutlet.repository.preference.PreferenceRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import kotlin.test.Test

class StoreCacheRepositoryTest : UnitTest<StoreCacheRepository>() {
    private val mockPreferenceRepository = mockk<PreferenceRepository>()
    private val mockTimeProvider = mockk<TimeProvider>()

    override fun buildSut() = StoreCacheRepository(mockPreferenceRepository, mockTimeProvider)

    @Test
    fun `should verify it store cache is valid - valid`() {
        val fixtureNow = LocalDateTime.now()
        every { mockTimeProvider.now() } returns fixtureNow

        val fixtureLastSyncDate = fixtureNow.minusDays(StoreCacheRepository.STORE_CACHE_VALIDITY_IN_DAYS - 1)

        every {
            mockPreferenceRepository.getPreference(StoreCacheRepository.KEY_STORE_LIST_CACHE)
        } returns fixtureLastSyncDate.toString()

        val actual = sut.isStoreListCacheValid()

        assertThat(actual).isTrue()
    }

    @Test
    fun `should verify it store cache is valid - invalid`() {
        val fixtureNow = LocalDateTime.now()
        every { mockTimeProvider.now() } returns fixtureNow

        val fixtureLastSyncDate = fixtureNow.minusDays(StoreCacheRepository.STORE_CACHE_VALIDITY_IN_DAYS + 1)

        every {
            mockPreferenceRepository.getPreference(StoreCacheRepository.KEY_STORE_LIST_CACHE)
        } returns fixtureLastSyncDate.toString()

        val actual = sut.isStoreListCacheValid()

        assertThat(actual).isFalse()
    }

    @Test
    fun `should verify it store cache as valid if there is no last sync`() {
        every { mockPreferenceRepository.getPreference(StoreCacheRepository.KEY_STORE_LIST_CACHE) } returns null

        val actual = sut.isStoreListCacheValid()

        assertThat(actual).isFalse()
    }

    @Test
    fun `should register store synchronized`() {
        val fixtureNow = LocalDateTime.now()
        val fixtureNowString = fixtureNow.toString()
        every { mockTimeProvider.now() } returns fixtureNow
        every {
            mockPreferenceRepository.setPreference(
                StoreCacheRepository.KEY_STORE_LIST_CACHE,
                fixtureNowString
            )
        } returns Unit

        sut.registerStoreListCached()

        verify { mockPreferenceRepository.setPreference(StoreCacheRepository.KEY_STORE_LIST_CACHE, fixtureNowString) }
    }
}
