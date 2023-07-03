package appoutlet.gameoutlet.core.util

import io.github.aakira.napier.Napier
import java.awt.Desktop
import java.net.URI

class DesktopHelper {
    fun openLink(url: String) {
        val desktop = Desktop.getDesktop()

        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(URI(url))
        } else {
            Napier.w("Desktop utils not supported. Trying with xdg-open")
            Runtime.getRuntime().exec(arrayOf("xdg-open", url))
        }
    }
}
