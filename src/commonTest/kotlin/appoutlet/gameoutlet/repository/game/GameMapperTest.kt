package appoutlet.gameoutlet.repository.game

import appoutlet.gameoutlet.core.database.GameEntity
import appoutlet.gameoutlet.core.testing.UnitTest
import com.google.common.truth.Truth
import kotlin.test.Test


internal class GameMapperTest : UnitTest<GameMapper>() {
    override fun buildSut() = GameMapper()

    @Test
    fun `should map game`() {
        val fixtureGameEntity = fixture<GameEntity>()

        val actual = sut(fixtureGameEntity)

        with(actual) {
            Truth.assertThat(id).isEqualTo(fixtureGameEntity.id)
            Truth.assertThat(title).isEqualTo(fixtureGameEntity.title)
            Truth.assertThat(image).isEqualTo(fixtureGameEntity.imageUrl)
        }
    }
}