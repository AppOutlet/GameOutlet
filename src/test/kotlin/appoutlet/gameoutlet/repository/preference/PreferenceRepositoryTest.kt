package appoutlet.gameoutlet.repository.preference

import app.cash.sqldelight.Query
import appoutlet.gameoutlet.core.database.PreferenceEntity
import appoutlet.gameoutlet.core.database.PreferenceQueries
import appoutlet.gameoutlet.core.testing.UnitTest
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class PreferenceRepositoryTest : UnitTest<PreferenceRepository>() {
    private val mockPreferenceQueries = mockk<PreferenceQueries>(relaxUnitFun = true)

    override fun buildSut() = PreferenceRepository(mockPreferenceQueries)

    @Test
    fun `should set preference`() {
        val fixtureKey = fixture<String>()
        val fixtureValue = fixture<String>()

        sut.setPreference(fixtureKey, fixtureValue)

        verify { mockPreferenceQueries.save(fixtureKey, fixtureValue) }
    }

    @Test
    fun `should get preference`() {
        val fixtureKey = fixture<String>()
        val fixtureValue = fixture<String>()
        val mockQueryPreference = mockk<Query<PreferenceEntity>>()

        every { mockPreferenceQueries.findByKey(fixtureKey) } returns mockQueryPreference
        every { mockQueryPreference.executeAsOneOrNull() } returns PreferenceEntity(fixtureKey, fixtureValue)

        val actual = sut.getPreference(fixtureKey)

        assertThat(actual).isEqualTo(fixtureValue)
    }

    @Test
    fun `should get preference - execution null`() {
        val fixtureKey = fixture<String>()
        val mockQueryPreference = mockk<Query<PreferenceEntity>>()

        every { mockPreferenceQueries.findByKey(fixtureKey) } returns mockQueryPreference
        every { mockQueryPreference.executeAsOneOrNull() } returns null

        val actual = sut.getPreference(fixtureKey)

        assertThat(actual).isNull()
    }
}
