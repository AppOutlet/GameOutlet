package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Game
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

internal class WishlistUiStateMapperTest : UnitTest<WishlistUiStateMapper>() {
    override fun buildSut() = WishlistUiStateMapper()

    @Test
    fun `should map wishlist ui state`() {
        val fixtureGames = fixture<List<Game>>()

        val actual = sut(fixtureGames)

        actual.forEachIndexed { index, wishlistGameUiModel ->
            val game = fixtureGames[index]

            with(wishlistGameUiModel) {
                assertThat(id).isEqualTo(game.id)
                assertThat(title).isEqualTo(game.title)
                assertThat(image).isEqualTo(game.image)
            }
        }
    }
}
