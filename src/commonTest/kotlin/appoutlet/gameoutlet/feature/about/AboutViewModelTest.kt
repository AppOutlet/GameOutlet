package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.core.util.DesktopHelper
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AboutViewModelTest : ViewModelTest<AboutViewModel>() {
    private val mockDesktopHelper = mockk<DesktopHelper>(relaxUnitFun = true)
    private val mockAboutViewDataMapper = mockk<AboutViewDataMapper>()

    override fun buildSut() = AboutViewModel(
        desktopHelper = mockDesktopHelper,
        aboutViewDataMapper = mockAboutViewDataMapper,
    )

    @Test
    fun `should load data`() = runViewModelTest {
        val fixtureData = fixture<AboutUiState.Loaded>()

        every { mockAboutViewDataMapper.invoke() } returns fixtureData

        sut.onInputEvent(AboutInputEvent.Load)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(fixtureData)
    }

    @Test
    fun `should open link`() = runViewModelTest {
        val fixtureEvent = fixture<AboutInputEvent.OpenLink>()

        sut.onInputEvent(fixtureEvent)

        verify { mockDesktopHelper.openLink(fixtureEvent.url) }
    }
}
