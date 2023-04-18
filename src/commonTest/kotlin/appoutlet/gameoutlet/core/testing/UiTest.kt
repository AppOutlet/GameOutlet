package appoutlet.gameoutlet.core.testing

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
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

    protected fun ComposeContentTestRule.print() {
        println(onRoot().printToString())
    }
}
