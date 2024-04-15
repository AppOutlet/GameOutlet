package appoutlet.gameoutlet.feature.gamesearch

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Game
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class GameSearchUiModelMapperTest : UnitTest<GameSearchUiModelMapper>() {
    override fun buildSut() = GameSearchUiModelMapper()

    @Test
    fun `should map ui model`() {
        val fixtureGame = fixture<Game>()

        val actual = sut(fixtureGame)

        assertThat(actual.id).isEqualTo(fixtureGame.id)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        assertThat(actual.title).isEqualTo(fixtureGame.title)
    }
}
