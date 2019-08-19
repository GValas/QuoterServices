package quoter.services.serviceA.pricers

import quoter.services.helper.BlackScholes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import quoter.services.model.Contract
import quoter.services.model.Quote
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class PricingService(@Autowired private val blackScholes: BlackScholes) {
    fun priceContract(contract: Contract): Quote {
        val dt = ChronoUnit.DAYS.between(LocalDateTime.now(), contract.maturity) / 365.25
        val r = contract.currency.rate
        val df = Math.exp(-r * dt)
        val f = contract.underlying.spot / df
        val v = contract.underlying.volatility
        val k = contract.strike
        val p = blackScholes.callPrice(f, k, df, v, dt)
        val d = blackScholes.callDelta(f, k, v, dt)
        return Quote(p, d, f, v, df)
    }
}


