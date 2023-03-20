package appoutlet.gameoutlet.core.translation

import java.io.InputStream

private fun ClassLoader.getStream(fileName: String): InputStream? {
    return getResourceAsStream("i18n/$fileName.po")
}

fun getTranslationFile(classLoader: ClassLoader, languageTag: String, language: String): InputStream {
    return classLoader.getStream(languageTag) ?: classLoader.getStream(language) ?: classLoader.getStream("en")!!
}
