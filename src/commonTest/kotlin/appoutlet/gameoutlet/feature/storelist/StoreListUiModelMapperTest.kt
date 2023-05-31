package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Store
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class StoreListUiModelMapperTest : UnitTest<StoreListUiModelMapper>() {

    override fun buildSut() = StoreListUiModelMapper()

    @Test
    fun `should map`() {
        val fixtureEntities = fixture<List<Store>>()

        val uiModels = sut.invoke(entities = fixtureEntities)

        uiModels.forEachIndexed { index, uiModel ->
            val entity = fixtureEntities[index]

            assertThat(uiModel.icon).isEqualTo(entity.logoUrl)
            assertThat(uiModel.name).isEqualTo(entity.name)
            assertThat(uiModel.inputEvent).isEqualTo(StoreListInputEvent.SelectStore(entity))
        }
    }
}