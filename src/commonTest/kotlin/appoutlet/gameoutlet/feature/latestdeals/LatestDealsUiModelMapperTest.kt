package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.latestdeals.composable.DealUiModel
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import name.kropp.kotlinx.gettext.I18n
import org.javamoney.moneta.Money
import java.math.BigDecimal
import kotlin.test.Test

class LatestDealsUiModelMapperTest : UnitTest<LatestDealsUiModelMapper>() {
    private val mockI18n = mockk<I18n>()

    override fun buildSut() = LatestDealsUiModelMapper(mockI18n)

    @Test
    fun `should map deals ui model`() {
        val gameFixture = fixture<Game>()
        val game2Fixture = fixture<Game>()
        val storeFixture = fixture<Store>()
        val store2Fixture = fixture<Store>()
        val currentPriceFixture = Money.of(BigDecimal(3), "USD")
        val oldPriceFixture = Money.of(BigDecimal(10), "USD")
        val currentCheapPriceFixture = Money.of(BigDecimal(0), "USD")
        val current2PriceFixture = Money.of(BigDecimal(2), "USD")
        val oldPrice2Fixture = Money.of(BigDecimal(12), "USD")
        val dealsFixture = listOf(
            fixture<Deal>().copy(
                game = gameFixture,
                store = storeFixture,
                salePrice = currentCheapPriceFixture,
                normalPrice = oldPriceFixture,
            ),

            fixture<Deal>().copy(
                game = gameFixture,
                store = storeFixture,
                salePrice = currentPriceFixture,
                normalPrice = oldPriceFixture,
            ),

            fixture<Deal>().copy(
                game = game2Fixture,
                store = store2Fixture,
                salePrice = current2PriceFixture,
                normalPrice = oldPrice2Fixture,
            ),
        )

        every { mockI18n.tr("FREE") } returns "FREE"

        val actual = sut.invoke(dealsFixture)

        assertThat(actual.size).isEqualTo(2)
        assertUiModel(
            uiModel = actual[0],
            game = gameFixture,
            expectedCurrentPrice = "FREE",
            expectedOldPrice = "$10.00",
            storeFixture,
            storeFixture
        )
        assertUiModel(
            uiModel = actual[1],
            game = game2Fixture,
            expectedCurrentPrice = "$2.00",
            expectedOldPrice = "$12.00",
            store2Fixture
        )
    }

    private fun assertUiModel(
        uiModel: DealUiModel,
        game: Game,
        expectedCurrentPrice: String,
        expectedOldPrice: String,
        vararg storeArray: Store
    ) {
        with(uiModel) {
            assertThat(gameTitle).isEqualTo(game.title)
            assertThat(gameId).isEqualTo(game.id)
            assertThat(gameImage).isEqualTo(game.image)
            stores.forEachIndexed { index, dealStoreUiModel ->
                assertThat(dealStoreUiModel.name).isEqualTo(storeArray[index].name)
                assertThat(dealStoreUiModel.icon).isEqualTo(storeArray[index].iconUrl)
            }
            assertThat(currentPrice).isEqualTo(expectedCurrentPrice)
            assertThat(oldPrice).isEqualTo(expectedOldPrice)
        }
    }
}
