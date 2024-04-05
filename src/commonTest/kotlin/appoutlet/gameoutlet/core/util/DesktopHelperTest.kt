package appoutlet.gameoutlet.core.util

import appoutlet.gameoutlet.core.testing.UnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.awt.Desktop
import java.net.URI
import kotlin.test.Test

class DesktopHelperTest : UnitTest<DesktopHelper>() {
    private val mockDesktop = mockk<Desktop>(relaxUnitFun = true)
    private val mockRuntime = mockk<Runtime>(relaxed = true)

    override fun buildSut() = DesktopHelper(mockDesktop, mockRuntime)

    @Test
    fun `openLink - browse supported`() {
        val fixtureLink = fixture<String>()

        every { mockDesktop.isSupported(Desktop.Action.BROWSE) } returns true

        sut.openLink(fixtureLink)

        verify {
            mockDesktop.browse(URI(fixtureLink))
        }
    }

    @Test
    fun `openLink - browse not supported`() {
        val fixtureLink = fixture<String>()

        every { mockDesktop.isSupported(Desktop.Action.BROWSE) } returns false

        sut.openLink(fixtureLink)

        verify {
            mockRuntime.exec(arrayOf("xdg-open", fixtureLink))
        }
    }
}
