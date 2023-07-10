package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import name.kropp.kotlinx.gettext.I18n
import org.javamoney.moneta.Money
import kotlin.math.roundToInt
import kotlin.test.Test

class GameUiModelMapperTest : UnitTest<GameUiModelMapper>() {
    private val mockI18n = mockk<I18n>()

    override fun buildSut() = GameUiModelMapper(mockI18n)

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

        val actual = sut.invoke(
            game = fixtureGame,
            deals = fixtureDeals,
            isGameSaved = false,
            shouldShowSnackbar = false,
        )

        assertThat(actual.title).isEqualTo(fixtureGame.title)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        actual.deals.forEachIndexed { index, actualDeal ->
            val fixtureDeal = fixtureDeals[index]

            assertThat(actualDeal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(actualDeal.salePrice).isEqualTo(fixtureDeal.salePrice.toBeTested())
            assertThat(actualDeal.normalPrice).isEqualTo(fixtureDeal.normalPrice.toBeTested())
            assertThat(actualDeal.showNormalPrice).isEqualTo(true)
            assertThat(actualDeal.savings).isEqualTo("- ${fixtureDeal.savings.roundToInt()}%")
            with(actualDeal.store) {
                assertThat(name).isEqualTo(fixtureDeal.store.name)
                assertThat(icon).isEqualTo(fixtureDeal.store.logoUrl)
            }
        }
        with(actual.favouriteButton) {
            assertThat(isSaved).isFalse()
            assertThat(inputEvent).isEqualTo(GameInputEvent.SaveGame(fixtureGame))
        }
        assertThat(actual.snackBarMessage).isNull()
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

        val actual = sut.invoke(
            game = fixtureGame,
            deals = fixtureDeals,
            isGameSaved = false,
            shouldShowSnackbar = false,
        )

        assertThat(actual.title).isEqualTo(fixtureGame.title)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        actual.deals.forEachIndexed { index, actualDeal ->
            val fixtureDeal = fixtureDeals[index]

            assertThat(actualDeal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(actualDeal.salePrice).isEqualTo(fixtureDeal.salePrice.toBeTested())
            assertThat(actualDeal.normalPrice).isEqualTo(fixtureDeal.normalPrice.toBeTested())
            assertThat(actualDeal.showNormalPrice).isEqualTo(false)
            assertThat(actualDeal.savings).isEqualTo("- ${fixtureDeal.savings.roundToInt()}%")
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

        val actual = sut.invoke(
            game = fixtureGame,
            deals = fixtureDeals,
            isGameSaved = false,
            shouldShowSnackbar = false,
        )

        assertThat(actual.title).isEqualTo(fixtureGame.title)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        actual.deals.forEachIndexed { index, actualDeal ->
            val fixtureDeal = fixtureDeals[index]

            assertThat(actualDeal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(actualDeal.salePrice).isEqualTo(fixtureDeal.salePrice.toBeTested())
            assertThat(actualDeal.normalPrice).isEqualTo(fixtureDeal.normalPrice.toBeTested())
            assertThat(actualDeal.showNormalPrice).isEqualTo(false)
            assertThat(actualDeal.savings).isEqualTo("- ${fixtureDeal.savings.roundToInt()}%")
            with(actualDeal.store) {
                assertThat(name).isEqualTo(fixtureDeal.store.name)
                assertThat(icon).isEqualTo("")
            }
        }
    }

    @Test
    fun `should map GameUiModel - FREE Game`() = runTest {
        val fixtureGame = fixture<Game>()
        val fixtureDeals = listOf(
            fixture<Deal>().copy(
                salePrice = Money.of(0, "USD"),
                normalPrice = Money.of(1, "USD"),
                store = fixture<Store>().copy(logoUrl = null),
            ),
        )

        every { mockI18n.tr("FREE") } returns "FREE"

        val actual = sut.invoke(
            game = fixtureGame,
            deals = fixtureDeals,
            isGameSaved = false,
            shouldShowSnackbar = false,
        )

        assertThat(actual.title).isEqualTo(fixtureGame.title)
        assertThat(actual.image).isEqualTo(fixtureGame.image)
        actual.deals.forEachIndexed { index, actualDeal ->
            val fixtureDeal = fixtureDeals[index]

            assertThat(actualDeal.id).isEqualTo(fixtureDeals[index].id)
            assertThat(actualDeal.salePrice).isEqualTo("FREE")
            assertThat(actualDeal.normalPrice).isEqualTo(fixtureDeal.normalPrice.toBeTested())
            assertThat(actualDeal.showNormalPrice).isEqualTo(true)
            assertThat(actualDeal.savings).isEqualTo("- ${fixtureDeal.savings.roundToInt()}%")
            with(actualDeal.store) {
                assertThat(name).isEqualTo(fixtureDeal.store.name)
                assertThat(icon).isEqualTo("")
            }
        }
    }

    @Test
    fun `should map GameUiModel - saved game`() = runTest {
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

        val actual = sut.invoke(
            game = fixtureGame,
            deals = fixtureDeals,
            isGameSaved = true,
            shouldShowSnackbar = false,
        )

        with(actual.favouriteButton) {
            assertThat(isSaved).isTrue()
            assertThat(inputEvent).isEqualTo(GameInputEvent.RemoveGameFromFavorites(fixtureGame))
        }
    }

    @Test
    fun `should map GameUiModel - snackbar saved`() = runTest {
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

        every {
            mockI18n.tr("The game was added to the favorites list")
        } returns "The game was added to the favorites list"

        val actual = sut.invoke(
            game = fixtureGame,
            deals = fixtureDeals,
            isGameSaved = true,
            shouldShowSnackbar = true,
        )

        assertThat(actual.snackBarMessage).isEqualTo("The game was added to the favorites list")
    }

    @Test
    fun `should map GameUiModel - snackbar not saved`() = runTest {
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

        every {
            mockI18n.tr("The game was removed from the favorites list")
        } returns "The game was removed from the favorites list"

        val actual = sut.invoke(
            game = fixtureGame,
            deals = fixtureDeals,
            isGameSaved = false,
            shouldShowSnackbar = true,
        )

        assertThat(actual.snackBarMessage).isEqualTo("The game was removed from the favorites list")
    }

    private fun Money.toBeTested(): String {
        return this.toString().replace("USD ", "$")
    }
}
