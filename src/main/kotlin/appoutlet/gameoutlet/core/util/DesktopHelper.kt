package appoutlet.gameoutlet.core.util

import io.github.aakira.napier.Napier
import java.awt.Desktop
import java.net.URI

class DesktopHelper(private val desktop: Desktop, private val runtime: Runtime) {
    fun openLink(url: String) {
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(URI(url))
        } else {
            Napier.w("Desktop utils not supported. Trying with xdg-open")
            runtime.exec(arrayOf("xdg-open", url))
        }
    }
}
