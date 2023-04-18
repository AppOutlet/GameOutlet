package appoutlet.gameoutlet.core.util

import java.awt.Desktop
import java.net.URI

class DesktopHelper {
    fun openLink(url: String) {
        Desktop.getDesktop().browse(URI(url))
    }
}
