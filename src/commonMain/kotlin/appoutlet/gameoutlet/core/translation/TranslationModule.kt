package appoutlet.gameoutlet.core.translation

import name.kropp.kotlinx.gettext.Gettext
import name.kropp.kotlinx.gettext.I18n
import name.kropp.kotlinx.gettext.Locale
import name.kropp.kotlinx.gettext.load
import org.koin.dsl.module
import java.io.InputStream

val translationModule = module {
    single { i18n }
}

val i18n: I18n by lazy {
    initGettext()
}

private fun initGettext(): I18n {
    val locale = Locale.getDefault()
    val languageTag = locale.toLanguageTag().replace("-", "_") // Needed because of the transifex language mapping
    val language = locale.language
    val classLoader = Thread.currentThread().contextClassLoader

    val translationFileInputStream = getTranslationFile(
        classLoader = classLoader,
        languageTag = languageTag,
        language = language,
    )

    return Gettext.load(locale = locale, s = translationFileInputStream)
}
