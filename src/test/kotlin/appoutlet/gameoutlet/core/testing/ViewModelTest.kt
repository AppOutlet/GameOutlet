package appoutlet.gameoutlet.core.testing

import appoutlet.gameoutlet.feature.common.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

abstract class ViewModelTest<T : Any> : UnitTest<T>() {
    protected val mockNavigator = mockk<Navigator>(relaxed = true)

    fun runViewModelTest(testBlock: TestScope.() -> Unit) = runTest {
        (sut as? ViewModel<*, *>)?.init(this, mockNavigator)
        testBlock()
        (sut as? ViewModel<*, *>)?.viewModelJob?.cancel()
    }
}
