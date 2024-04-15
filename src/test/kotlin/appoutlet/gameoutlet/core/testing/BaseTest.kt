package appoutlet.gameoutlet.core.testing

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import org.javamoney.moneta.Money
import java.math.BigDecimal
import java.time.LocalDateTime

@Suppress("UnnecessaryAbstractClass")
abstract class BaseTest {
    protected val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        factory<Money> {
            Money.of(BigDecimal((1..100).random()), "USD")
        }
        factory<LocalDateTime> {
            LocalDateTime.now()
        }
    }
}
