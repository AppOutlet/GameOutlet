package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Store
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class StoreEntityMapperTest : UnitTest<StoreEntityMapper>() {
    override fun buildSut() = StoreEntityMapper()

    @Test
    fun `should map store entity`() {
        val fixtureStore = fixture<Store>()

        val actual = sut.invoke(fixtureStore)

        assertThat(actual.id).isEqualTo(fixtureStore.id)
        assertThat(actual.name).isEqualTo(fixtureStore.name)
        assertThat(actual.bannerUrl).isEqualTo(fixtureStore.bannerUrl)
        assertThat(actual.logoUrl).isEqualTo(fixtureStore.logoUrl)
        assertThat(actual.iconUrl).isEqualTo(fixtureStore.iconUrl)
    }
}
