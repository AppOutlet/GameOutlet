package appoutlet.gameoutlet.core.testing

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture

abstract class BaseTest {
    protected val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }
}
