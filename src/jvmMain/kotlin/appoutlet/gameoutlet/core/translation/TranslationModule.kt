package appoutlet.gameoutlet.core.translation

import name.kropp.kotlinx.gettext.Gettext
import name.kropp.kotlinx.gettext.I18n
import name.kropp.kotlinx.gettext.Locale
import name.kropp.kotlinx.gettext.load
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.InputStream

private const val QUALIFIER_LANGUAGE_TAG = "languageTag"
private const val QUALIFIER_LANGUAGE = "language"

val translationModule = module {
    factory { Locale.getDefault() }

    factory(named(QUALIFIER_LANGUAGE_TAG)) {
        val locale = get<Locale>()
        locale.toLanguageTag().replace("-","_")// Needed because of the transifex language mapping
    }

    factory(named(QUALIFIER_LANGUAGE)) {
        val locale = get<Locale>()
        locale.language
    }

    factory { Thread.currentThread().contextClassLoader }

    single<I18n> {
        val locale = get<Locale>()
        val languageTag = get<String>(named(QUALIFIER_LANGUAGE_TAG))
        val language = get<String>(named(QUALIFIER_LANGUAGE))
        val classLoader = get<ClassLoader>()

        val translationFileInputStream = getTranslationFile(
            classLoader = classLoader,
            languageTag = languageTag,
            language = language,
        )

        Gettext.load(locale = locale, s = translationFileInputStream)
    }
}

private fun ClassLoader.getStream(fileName: String): InputStream? {
    return getResourceAsStream("$fileName.po")
}

fun getTranslationFile(classLoader: ClassLoader, languageTag: String, language: String): InputStream {
    return classLoader.getStream(languageTag) ?: classLoader.getStream(language) ?: classLoader.getStream("en")!!
}
