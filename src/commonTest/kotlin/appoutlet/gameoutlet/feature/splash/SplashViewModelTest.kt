package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.feature.home.HomeView
import appoutlet.gameoutlet.feature.home.HomeViewProvider
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class SplashViewModelTest : ViewModelTest<SplashViewModel>() {
    private val mockSplashOrchestrator = mockk<SplashOrchestrator>()
    private val mockHomeViewProvider = mockk<HomeViewProvider>()
    private val mockHomeView = mockk<HomeView>()

    override fun buildSut() = SplashViewModel(
        splashOrchestrator = mockSplashOrchestrator,
        homeViewProvider = mockHomeViewProvider
    )

    @Test
    fun `should sync stores`() = runViewModelTest {
        every { mockSplashOrchestrator.synchronizeStoreData() } returns flow {
            delay(3)
            emit(Unit)
        }

        every { mockHomeViewProvider.getView() } returns mockHomeView

        assertThat(sut.uiState.value).isEqualTo(SplashUiState.Idle)

        sut.onInputEvent(SplashInputEvent.Load)

        advanceTimeBy(2)

        assertThat(sut.uiState.value).isEqualTo(SplashUiState.Loading)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(SplashUiState.Loaded)

        verify { mockNavigator.replaceAll(mockHomeView) }
    }

    @Test
    fun `should show error screen`() = runTest {
        sut.init(this, mockNavigator)

        every { mockSplashOrchestrator.synchronizeStoreData() } returns flow {
            throw IllegalStateException()
        }

        sut.onInputEvent(SplashInputEvent.Load)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(SplashUiState.Error)
    }
}
