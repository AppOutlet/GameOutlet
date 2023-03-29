package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.core.util.TimeProvider
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.api.DealResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.time.LocalDateTime
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DealMapperTest : UnitTest<DealMapper>() {
    private val mockDealGameMapper = mockk<DealGameMapper>()
    private val mockDealStoreMapper = mockk<DealStoreMapper>()
    private val mockTimeProvider = mockk<TimeProvider>()
    override fun buildSut() = DealMapper(
        dealGameMapper = mockDealGameMapper,
        dealStoreMapper = mockDealStoreMapper,
        timeProvider = mockTimeProvider,
    )

    @Test
    fun `should map response list`() = runTest {
        val fixtureResponses = listOf(
            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),

            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),

            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),
        )
        val fixtureGames = fixture<List<Game>>()
        val fixtureStores = fixture<List<Store>>()
        val fixtureLocalDateTime = fixture<LocalDateTime>()

        fixtureResponses.forEachIndexed { index, dealResponse ->
            every { mockDealGameMapper.invoke(dealResponse) } returns fixtureGames[index]
            every { mockDealStoreMapper.invoke(dealResponse) } returns fixtureStores[index]
            every { mockTimeProvider.fromEpochMillis(dealResponse.releaseDate) } returns fixtureLocalDateTime
            every { mockTimeProvider.fromEpochMillis(dealResponse.lastChange) } returns fixtureLocalDateTime
        }

        val actual = sut.invoke(fixtureResponses)

        actual.forEachIndexed { index, deal ->
            val response = fixtureResponses[index]
            val fixtureGame = fixtureGames[index]
            val fixtureStore = fixtureStores[index]

            with(deal) {
                assertThat(id).isEqualTo(response.dealID)
                assertThat(game).isEqualTo(fixtureGame)
                assertThat(store).isEqualTo(fixtureStore)
                assertThat(savings).isEqualTo(1)
                assertThat(releaseDate).isEqualTo(fixtureLocalDateTime)
                assertThat(lastChange).isEqualTo(fixtureLocalDateTime)
                assertThat(rating).isEqualTo(1)
            }
        }
    }

    @Test
    fun `should not map if the game is missing`() = runTest {
        val fixtureResponses = listOf(
            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),

            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),

            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),
        )
        val fixtureGame = fixture<Game>()
        val fixtureStore = fixture<Store>()
        val fixtureLocalDateTime = fixture<LocalDateTime>()

        fixtureResponses.forEach { dealResponse ->
            every { mockDealGameMapper.invoke(dealResponse) } returns null
            every { mockDealStoreMapper.invoke(dealResponse) } returns fixtureStore
            every { mockTimeProvider.fromEpochMillis(dealResponse.releaseDate) } returns fixtureLocalDateTime
            every { mockTimeProvider.fromEpochMillis(dealResponse.lastChange) } returns fixtureLocalDateTime
        }
        every { mockDealGameMapper.invoke(fixtureResponses.first()) } returns fixtureGame

        val actual = sut.invoke(fixtureResponses)

        assertThat(actual.size).isEqualTo(1)
        val response = fixtureResponses.first()
        with(actual.first()) {
            assertThat(id).isEqualTo(response.dealID)
            assertThat(game).isEqualTo(fixtureGame)
            assertThat(store).isEqualTo(fixtureStore)
            assertThat(savings).isEqualTo(1)
            assertThat(releaseDate).isEqualTo(fixtureLocalDateTime)
            assertThat(lastChange).isEqualTo(fixtureLocalDateTime)
            assertThat(rating).isEqualTo(1)
        }
    }

    @Test
    fun `should not map if the store is missing`() = runTest {
        val fixtureResponses = listOf(
            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),

            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),

            fixture<DealResponse>().copy(
                salePrice = fixture<Int>().toString(),
                normalPrice = fixture<Int>().toString(),
                savings = "1",
                dealRating = "1",
            ),
        )
        val fixtureGame = fixture<Game>()
        val fixtureStore = fixture<Store>()
        val fixtureLocalDateTime = fixture<LocalDateTime>()

        fixtureResponses.forEach { dealResponse ->
            every { mockDealGameMapper.invoke(dealResponse) } returns fixtureGame
            every { mockDealStoreMapper.invoke(dealResponse) } returns null
            every { mockTimeProvider.fromEpochMillis(dealResponse.releaseDate) } returns fixtureLocalDateTime
            every { mockTimeProvider.fromEpochMillis(dealResponse.lastChange) } returns fixtureLocalDateTime
        }
        every { mockDealStoreMapper.invoke(fixtureResponses.first()) } returns fixtureStore

        val actual = sut.invoke(fixtureResponses)

        assertThat(actual.size).isEqualTo(1)
        val response = fixtureResponses.first()
        with(actual.first()) {
            assertThat(id).isEqualTo(response.dealID)
            assertThat(game).isEqualTo(fixtureGame)
            assertThat(store).isEqualTo(fixtureStore)
            assertThat(savings).isEqualTo(1)
            assertThat(releaseDate).isEqualTo(fixtureLocalDateTime)
            assertThat(lastChange).isEqualTo(fixtureLocalDateTime)
            assertThat(rating).isEqualTo(1)
        }
    }
}
