package appoutlet.gameoutlet.core.util

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import java.awt.Desktop

val utilModule = module {
    factory { TimeProvider() }
    factoryOf(::DesktopHelper)
    factory { Desktop.getDesktop() }
    factory { Runtime.getRuntime() }
}
