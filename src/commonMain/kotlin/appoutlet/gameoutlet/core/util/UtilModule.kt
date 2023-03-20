package appoutlet.gameoutlet.core.util

import org.koin.dsl.module

val utilModule = module {
    factory { TimeProvider() }
}
