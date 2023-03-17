package appoutlet.gameoutlet.core.translation

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.io.InputStream
import kotlin.test.Test


class TranslationFileProviderKtTest {
    private val mockClassLoader = mockk<ClassLoader>()
    private val fixtureInputStream = InputStream.nullInputStream()

    @Test
    fun `should return translation file - language tag`() {
        val fixtureLanguageTag = "en-GB"
        val fixtureLanguage = "en"
        val fixtureLanguageTagFileName = "i18n/$fixtureLanguageTag.po"
        val fixtureLanguageFileName = "i18n/$fixtureLanguage.po"

        every { mockClassLoader.getResourceAsStream(fixtureLanguageTagFileName) } returns fixtureInputStream

        val actual = getTranslationFile(
            classLoader = mockClassLoader,
            languageTag = fixtureLanguageTag,
            language = fixtureLanguage
        )

        assertThat(actual).isEqualTo(fixtureInputStream)
        verify { mockClassLoader.getResourceAsStream(fixtureLanguageTagFileName) }
        verify(exactly = 0) { mockClassLoader.getResourceAsStream(fixtureLanguageFileName) }
    }

    @Test
    fun `should return translation file - language`() {
        val fixtureLanguageTag = "pt-BR"
        val fixtureLanguage = "pt"
        val fixtureLanguageTagFileName = "i18n/$fixtureLanguageTag.po"
        val fixtureLanguageFileName = "i18n/$fixtureLanguage.po"
        val fixtureDefaultLanguageFileName =  "i18n/en.po"

        every { mockClassLoader.getResourceAsStream(fixtureLanguageTagFileName) } returns null
        every { mockClassLoader.getResourceAsStream(fixtureLanguageFileName) } returns fixtureInputStream

        val actual = getTranslationFile(
            classLoader = mockClassLoader,
            languageTag = fixtureLanguageTag,
            language = fixtureLanguage
        )

        assertThat(actual).isEqualTo(fixtureInputStream)
        verify { mockClassLoader.getResourceAsStream(fixtureLanguageTagFileName) }
        verify { mockClassLoader.getResourceAsStream(fixtureLanguageFileName) }
        verify(exactly = 0) { mockClassLoader.getResourceAsStream(fixtureDefaultLanguageFileName) }
    }

    @Test
    fun `should return translation file - fallback language`() {
        val fixtureLanguageTag = "es-AR"
        val fixtureLanguage = "es"
        val fixtureLanguageTagFileName = "i18n/$fixtureLanguageTag.po"
        val fixtureLanguageFileName = "i18n/$fixtureLanguage.po"
        val fixtureDefaultLanguageFileName =  "i18n/en.po"

        every { mockClassLoader.getResourceAsStream(fixtureLanguageTagFileName) } returns null
        every { mockClassLoader.getResourceAsStream(fixtureLanguageFileName) } returns null
        every { mockClassLoader.getResourceAsStream(fixtureDefaultLanguageFileName) } returns fixtureInputStream

        val actual = getTranslationFile(
            classLoader = mockClassLoader,
            languageTag = fixtureLanguageTag,
            language = fixtureLanguage
        )

        assertThat(actual).isEqualTo(fixtureInputStream)
        verify { mockClassLoader.getResourceAsStream(fixtureLanguageTagFileName) }
        verify { mockClassLoader.getResourceAsStream(fixtureLanguageFileName) }
        verify { mockClassLoader.getResourceAsStream(fixtureDefaultLanguageFileName) }
    }
}
