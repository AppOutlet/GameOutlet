package appoutlet.gameoutlet.repository.game

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Game
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

internal class GameEntityMapperTest : UnitTest<GameEntityMapper>() {
    override fun buildSut() = GameEntityMapper()

    @Test
    fun `should map game entity`() {
        val fixtureGame = fixture<Game>()

        val actual = sut(fixtureGame)

        with(actual) {
            assertThat(id).isEqualTo(fixtureGame.id)
            assertThat(title).isEqualTo(fixtureGame.title)
            assertThat(imageUrl).isEqualTo(fixtureGame.image)
        }
    }
}
