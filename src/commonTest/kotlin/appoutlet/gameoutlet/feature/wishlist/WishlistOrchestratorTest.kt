package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.repository.game.GameRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test

internal class WishlistOrchestratorTest : UnitTest<WishlistOrchestrator>() {
    private val mockGameRepository = mockk<GameRepository>()

    override fun buildSut() = WishlistOrchestrator(gameRepository = mockGameRepository)

    @Test
    fun `should find all games`() {
        val fixtureGames = fixture<List<Game>>()

        every { mockGameRepository.findAll() } returns fixtureGames

        val actual = sut.findAll()

        assertThat(actual).isEqualTo(fixtureGames)
    }
}