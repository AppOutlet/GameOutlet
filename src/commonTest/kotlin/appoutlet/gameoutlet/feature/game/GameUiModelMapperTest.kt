package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.javamoney.moneta.Money
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameUiModelMapperTest : UnitTest<GameUiModelMapper>() {
    override fun buildSut() = GameUiModelMapper()

    @Test
    fun `should map GameUiModel`() = runTest {
        val fixtureGame = fixture<Game>()
        val fixtureDeals = listOf(
            fixture<Deal>().copy(
                salePrice = Money.of(1, "USD"),
                normalPrice = Money.of(2, "USD"),
                store = fixture<Store>().copy(logoUrl = "logo url"),
            ),
            fixture<Deal>().copy(
                salePrice = Money.of(3, "USD"),
                normalPrice = Money.of(4, "USD"),
                store = fixture<Store>().copy(logoUrl = "logo url"),
            ),
            fixture<Deal>().copy(
                salePrice = Money.of(5, "USD"),
                normalPrice = Money.of(6, "USD"),
                store = fixture<Store>().copy(logoUrl = "logo url"),
            ),
        )

        val actual = sut.invoke(fixtureGame, fixtureDeals)

        assertThat(actual.title).isEqualTo(fixtureGame.title)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        actual.deals.forEachIndexed { index, actualDeal ->
            val fixtureDeal = fixtureDeals[index]

            assertThat(actualDeal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(actualDeal.salePrice).isEqualTo(fixtureDeal.salePrice.toBeTested())
            assertThat(actualDeal.normalPrice).isEqualTo(fixtureDeal.normalPrice.toBeTested())
            assertThat(actualDeal.showNormalPrice).isEqualTo(true)
            with(actualDeal.store) {
                assertThat(name).isEqualTo(fixtureDeal.store.name)
                assertThat(icon).isEqualTo(fixtureDeal.store.logoUrl)
            }
        }
    }

    @Test
    fun `should map GameUiModel - same price`() = runTest {
        val fixtureGame = fixture<Game>()
        val fixtureDeals = listOf(
            fixture<Deal>().copy(
                salePrice = Money.of(1, "USD"),
                normalPrice = Money.of(1, "USD"),
                store = fixture<Store>().copy(logoUrl = "logo url"),
            ),
        )

        val actual = sut.invoke(fixtureGame, fixtureDeals)

        assertThat(actual.title).isEqualTo(fixtureGame.title)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        actual.deals.forEachIndexed { index, actualDeal ->
            val fixtureDeal = fixtureDeals[index]

            assertThat(actualDeal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(actualDeal.salePrice).isEqualTo(fixtureDeal.salePrice.toBeTested())
            assertThat(actualDeal.normalPrice).isEqualTo(fixtureDeal.normalPrice.toBeTested())
            assertThat(actualDeal.showNormalPrice).isEqualTo(false)
            with(actualDeal.store) {
                assertThat(name).isEqualTo(fixtureDeal.store.name)
                assertThat(icon).isEqualTo(fixtureDeal.store.logoUrl)
            }
        }
    }

    @Test
    fun `should map GameUiModel - no logo`() = runTest {
        val fixtureGame = fixture<Game>()
        val fixtureDeals = listOf(
            fixture<Deal>().copy(
                salePrice = Money.of(1, "USD"),
                normalPrice = Money.of(1, "USD"),
                store = fixture<Store>().copy(logoUrl = null),
            ),
        )

        val actual = sut.invoke(fixtureGame, fixtureDeals)

        assertThat(actual.title).isEqualTo(fixtureGame.title)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        actual.deals.forEachIndexed { index, actualDeal ->
            val fixtureDeal = fixtureDeals[index]

            assertThat(actualDeal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(actualDeal.salePrice).isEqualTo(fixtureDeal.salePrice.toBeTested())
            assertThat(actualDeal.normalPrice).isEqualTo(fixtureDeal.normalPrice.toBeTested())
            assertThat(actualDeal.showNormalPrice).isEqualTo(false)
            with(actualDeal.store) {
                assertThat(name).isEqualTo(fixtureDeal.store.name)
                assertThat(icon).isEqualTo("")
            }
        }
    }

    private fun Money.toBeTested(): String {
        return this.toString().replace("USD ", "$")
    }
}
