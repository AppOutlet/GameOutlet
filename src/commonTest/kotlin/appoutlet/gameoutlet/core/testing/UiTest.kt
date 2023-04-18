package appoutlet.gameoutlet.core.testing

import androidx.compose.ui.test.junit4.createComposeRule
import appoutlet.gameoutlet.feature.game.GameInputEvent
import io.mockk.mockk
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class UiTest : BaseTest() {
    @get:Rule
    val composeTestRule = createComposeRule()
    open val koinTestModule: Module = module { }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(koinTestModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}
