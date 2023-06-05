package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.feature.common.util.asString
import com.google.common.truth.Truth.assertThat
import org.javamoney.moneta.Money
import kotlin.test.Test

class StoreViewDataMapperTest : UnitTest<StoreViewDataMapper>() {
    override fun buildSut() = StoreViewDataMapper()

    @Test
    fun `should map`() {
        val fixtureDeals = fixture<List<Deal>>()

        val actual = sut(fixtureDeals)

        actual.deals.forEachIndexed { index, dealViewData ->
            val deal = fixtureDeals[index]

            assertThat(dealViewData.title).isEqualTo(deal.game.title)
            assertThat(dealViewData.currentPrice).isEqualTo(deal.salePrice.asString())
            assertThat(dealViewData.normalPrice).isEqualTo(deal.normalPrice.asString())
            assertThat(dealViewData.image).isEqualTo(deal.game.image)

            with(dealViewData.inputEvent) {
                val event = this as StoreInputEvent.SelectDeal

                assertThat(event.gameNavArgs.gameId).isEqualTo(deal.game.id)
                assertThat(event.gameNavArgs.gameImage).isEqualTo(deal.game.image)
                assertThat(event.gameNavArgs.gameTitle).isEqualTo(deal.game.title)
            }
        }
    }

    @Test
    fun `should map - same prices`() {
        val fixturePrice = fixture<Money>()
        val fixtureDeals = listOf(
            fixture<Deal>().copy(normalPrice = fixturePrice, salePrice = fixturePrice),
        )

        val actual = sut(fixtureDeals)

        actual.deals.forEach { dealViewData ->
            assertThat(dealViewData.normalPrice).isNull()
        }
    }
}
