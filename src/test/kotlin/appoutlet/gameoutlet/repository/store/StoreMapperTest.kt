package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.database.StoreEntity
import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.repository.store.StoreMapper.Companion.CHEAP_SHARK_IMAGE_BASE_URL
import appoutlet.gameoutlet.repository.store.api.model.StoreResponse
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class StoreMapperTest : UnitTest<StoreMapper>() {
    override fun buildSut() = StoreMapper()

    @Test
    fun `should map from StoreEntity`() {
        val fixtureStoreEntity = fixture<StoreEntity>().copy(id = fixture<Int>().toLong())

        val actual = sut.invoke(fixtureStoreEntity)

        assertThat(actual.id).isEqualTo(fixtureStoreEntity.id)
        assertThat(actual.name).isEqualTo(fixtureStoreEntity.name)
        assertThat(actual.bannerUrl).isEqualTo(fixtureStoreEntity.bannerUrl)
        assertThat(actual.logoUrl).isEqualTo(fixtureStoreEntity.logoUrl)
        assertThat(actual.iconUrl).isEqualTo(fixtureStoreEntity.iconUrl)
    }

    @Test
    fun `should map from StoreResponse`() {
        val fixtureStoreEntity = fixture<StoreResponse>().copy(storeID = fixture<Int>().toString())

        val actual = sut.invoke(fixtureStoreEntity)

        assertThat(actual.id).isEqualTo(fixtureStoreEntity.storeID.toInt())
        assertThat(actual.name).isEqualTo(fixtureStoreEntity.storeName)
        assertThat(actual.bannerUrl).isEqualTo(CHEAP_SHARK_IMAGE_BASE_URL + fixtureStoreEntity.images.banner)
        assertThat(actual.logoUrl).isEqualTo(CHEAP_SHARK_IMAGE_BASE_URL + fixtureStoreEntity.images.logo)
        assertThat(actual.iconUrl).isEqualTo(CHEAP_SHARK_IMAGE_BASE_URL + fixtureStoreEntity.images.icon)
    }
}
