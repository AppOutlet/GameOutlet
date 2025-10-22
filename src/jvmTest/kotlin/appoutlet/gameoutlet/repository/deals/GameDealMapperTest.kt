package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.core.util.TimeProvider
import appoutlet.gameoutlet.core.util.asMoney
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.api.GameDealResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import kotlin.test.Test

internal class GameDealMapperTest : UnitTest<GameDealMapper>() {
    private val mockTimeProvider = mockk<TimeProvider>()

    override fun buildSut() = GameDealMapper(
        timeProvider = mockTimeProvider
    )

    @Test
    fun `should map deal`() {
        val fixtureStoreId = fixture<Int>()
        val fixtureGame = fixture<Game>()
        val fixtureGameDealResponse = fixture<GameDealResponse>().copy(
            storeID = fixtureStoreId.toString(),
            price = "10",
            retailPrice = "100",
            savings = "90"
        )
        val fixtureNow = LocalDateTime.now()

        every { mockTimeProvider.now() } returns fixtureNow

        val actual = sut(game = fixtureGame, gameDealResponse = fixtureGameDealResponse)

        assertThat(actual).isNotNull()
        actual?.run {
            assertThat(id).isEqualTo(fixtureGameDealResponse.dealID)
            assertThat(game).isEqualTo(fixtureGame)
            assertThat(store).isEqualTo(Store(fixtureStoreId))
            assertThat(salePrice).isEqualTo("10".asMoney())
            assertThat(normalPrice).isEqualTo("100".asMoney())
            assertThat(savings).isEqualTo("90".toFloat())
            assertThat(releaseDate).isEqualTo(fixtureNow)
            assertThat(lastChange).isEqualTo(fixtureNow)
            assertThat(rating).isEqualTo(0f)
        }
    }

    @Test
    fun `should return null if store id in not a int`() {
        val fixtureGame = fixture<Game>()
        val fixtureGameDealResponse = fixture<GameDealResponse>()

        val actual = sut(game = fixtureGame, gameDealResponse = fixtureGameDealResponse)

        assertThat(actual).isNull()
    }

    @Test
    fun `should return savings as 0 if it is not valid`() {
        val fixtureStoreId = fixture<Int>()
        val fixtureGame = fixture<Game>()
        val fixtureGameDealResponse = fixture<GameDealResponse>().copy(
            storeID = fixtureStoreId.toString(),
            price = "10",
            retailPrice = "100",
        )
        val fixtureNow = LocalDateTime.now()

        every { mockTimeProvider.now() } returns fixtureNow

        val actual = sut(game = fixtureGame, gameDealResponse = fixtureGameDealResponse)

        assertThat(actual).isNotNull()
        actual?.run {
            assertThat(id).isEqualTo(fixtureGameDealResponse.dealID)
            assertThat(game).isEqualTo(fixtureGame)
            assertThat(store).isEqualTo(Store(fixtureStoreId))
            assertThat(salePrice).isEqualTo("10".asMoney())
            assertThat(normalPrice).isEqualTo("100".asMoney())
            assertThat(savings).isEqualTo(0f)
            assertThat(releaseDate).isEqualTo(fixtureNow)
            assertThat(lastChange).isEqualTo(fixtureNow)
            assertThat(rating).isEqualTo(0f)
        }
    }
}
