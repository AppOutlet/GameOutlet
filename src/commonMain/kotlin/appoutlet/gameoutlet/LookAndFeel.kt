package appoutlet.gameoutlet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.FrameWindowScope
import com.formdev.flatlaf.util.SystemInfo
import javax.swing.JRootPane

fun initLookAndFeel() {
    when (getOS()) {
        OS.MAC -> setupMacLookAndFeel()
        OS.WINDOWS -> {
        }

        OS.LINUX -> {
        }
    }
}

private fun setupMacLookAndFeel() {
    System.setProperty("apple.laf.useScreenMenuBar", "true")
    System.setProperty("apple.awt.application.name", "GameOutlet")
    System.setProperty("apple.awt.application.appearance", "system")
}

@Composable
fun FrameWindowScope.setupWindowLookAndFeel() {
    when (getOS()) {
        OS.MAC -> setupMacWindowLookAndFeel(window.rootPane)
        OS.WINDOWS -> {
        }

        OS.LINUX -> {
            FixIconOnLinuxHosts(window)
        }
    }
}

@Suppress("MagicNumber", "ForbiddenComment")
@Composable
private fun FixIconOnLinuxHosts(window: ComposeWindow) {
    // TODO: remove when https://github.com/JetBrains/compose-multiplatform/issues/1838 is solved
    val icon = painterResource("image/icon.png")
    val density = LocalDensity.current
    SideEffect {
        window.iconImage = icon.toAwtImage(density, LayoutDirection.Ltr, Size(128f, 128f))
    }
}

private fun setupMacWindowLookAndFeel(rootPane: JRootPane) {
    rootPane.putClientProperty("apple.awt.fullWindowContent", true)
    rootPane.putClientProperty("apple.awt.transparentTitleBar", true)
    rootPane.putClientProperty("apple.awt.windowTitleVisible", false)
}

enum class OS {
    MAC, WINDOWS, LINUX
}

private fun getOS(): OS {
    return when {
        SystemInfo.isWindows -> OS.WINDOWS
        SystemInfo.isLinux -> OS.LINUX
        SystemInfo.isMacOS -> OS.MAC
        else -> OS.LINUX
    }
}
