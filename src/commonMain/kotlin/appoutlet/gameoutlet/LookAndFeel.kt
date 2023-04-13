package appoutlet.gameoutlet

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

fun FrameWindowScope.setupWindowLookAndFeel() {
    when (getOS()) {
        OS.MAC -> setupMacWindowLookAndFeel(window.rootPane)
        OS.WINDOWS -> {
        }

        OS.LINUX -> {
        }
    }
}

private fun setupMacWindowLookAndFeel(rootPane: JRootPane) {
    rootPane.putClientProperty("apple.awt.fullWindowContent", true)
    rootPane.putClientProperty("apple.awt.transparentTitleBar", true)
    rootPane.putClientProperty("apple.awt.windowTitleVisible", false)
}

enum class OS {
    MAC, WINDOWS, LINUX;
}

private fun getOS(): OS {
    return when {
        SystemInfo.isWindows -> OS.WINDOWS
        SystemInfo.isLinux -> OS.LINUX
        SystemInfo.isMacOS -> OS.MAC
        else -> OS.LINUX
    }
}
