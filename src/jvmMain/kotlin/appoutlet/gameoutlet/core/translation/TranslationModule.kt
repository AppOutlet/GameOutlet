package appoutlet.gameoutlet.core.translation

import name.kropp.kotlinx.gettext.Gettext
import name.kropp.kotlinx.gettext.Locale
import name.kropp.kotlinx.gettext.load
import org.koin.dsl.module

val translationModule = module {
    single {
        val locale = Locale.getDefault()
        val language = locale.toLanguageTag()

        Gettext.load(
            locale = locale,
            Thread.currentThread().contextClassLoader.getResourceAsStream("$language.po")!!
        )
    }
}
