package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.repository.deals.api.DealResponse
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class DealGameMapperTest : UnitTest<DealGameMapper>() {
    override fun buildSut() = DealGameMapper()

    @Test
    fun `should map a game`() {
        val responseFixture = fixture<DealResponse>().copy(gameID = "1")

        val actual = sut.invoke(dealResponse = responseFixture)

        assertThat(actual).isNotNull()
        with(actual!!) {
            assertThat(id).isEqualTo(1)
            assertThat(title).isEqualTo(responseFixture.title)
            assertThat(image).isEqualTo(responseFixture.thumb)
            assertThat(metacritic).isNull()
            assertThat(steam).isNull()
        }
    }

    @Test
    fun `should return null if game id is not a number`() {
        val responseFixture = fixture<DealResponse>().copy(gameID = "sdfasd")

        val actual = sut.invoke(dealResponse = responseFixture)

        assertThat(actual).isNull()
    }

    @Test
    fun `should replace image url fragment`() {
        val responseFixture = fixture<DealResponse>().copy(gameID = "1", thumb = "capsule_sm_120")

        val actual = sut.invoke(dealResponse = responseFixture)

        assertThat(actual).isNotNull()
        assertThat(actual!!.image).isEqualTo("header")
    }
}
