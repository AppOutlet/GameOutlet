package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.api.DealResponse
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class DealStoreMapperTest : UnitTest<DealStoreMapper>() {
    override fun buildSut() = DealStoreMapper()

    @Test
    fun `should map store`() {
        val fixtureResponse = fixture<DealResponse>().copy(storeID = "1")

        val actual = sut.invoke(fixtureResponse)

        assertThat(actual).isEqualTo(Store(1))
    }

    @Test
    fun `should return null if the store id is not a valid int`() {
        val fixtureResponse = fixture<DealResponse>()

        val actual = sut.invoke(fixtureResponse)

        assertThat(actual).isNull()
    }
}