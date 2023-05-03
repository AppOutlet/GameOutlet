package appoutlet.gameoutlet.repository.deals


import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.repository.deals.api.GameSearchResponse
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

internal class GameMapperTest : UnitTest<GameMapper>() {
    override fun buildSut() = GameMapper()

    @Test
    fun `should map game`() {
        val fixtureGameId = fixture<Long>()
        val fixtureThumb = fixture<String>()

        val fixtureGameResponse = fixture<GameSearchResponse>().copy(
            gameID = fixtureGameId.toString(),
            thumb = fixtureThumb + "capsule_sm_120"
        )

        val actual = sut(fixtureGameResponse)

        assertThat(actual).isNotNull()
        actual?.run {
            assertThat(id).isEqualTo(fixtureGameId)
            assertThat(title).isEqualTo(fixtureGameResponse.external)
            assertThat(image).isEqualTo(fixtureThumb + "header")
        }
    }

    @Test
    fun `should map game - invalid id`() {
        val fixtureGameResponse = fixture<GameSearchResponse>()

        val actual = sut(fixtureGameResponse)

        assertThat(actual).isNull()
    }
}