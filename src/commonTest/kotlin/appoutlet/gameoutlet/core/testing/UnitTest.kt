package appoutlet.gameoutlet.core.testing

import kotlin.test.BeforeTest

abstract class UnitTest<SubjectUnderTest : Any> : BaseTest() {
    protected lateinit var sut: SubjectUnderTest

    abstract fun buildSut(): SubjectUnderTest

    @BeforeTest
    fun setup() {
        sut = buildSut()
    }
}
