package quoter.services.helper

import org.apache.commons.math3.distribution.NormalDistribution
import org.springframework.stereotype.Component
import kotlin.math.ln
import kotlin.math.sqrt

@Component
object BlackScholes {

    private val normalDistrib = NormalDistribution()

    fun callPrice(
            forward: Double,
            strike: Double,
            discountFactor: Double,
            volatility: Double,
            timeToMaturity: Double
    ): Double {
        val vt = volatility * sqrt(timeToMaturity)
        val d1 = ln(forward / strike) / vt + vt / 2
        val d2 = d1 - vt
        val nd1 = normalDistrib.cumulativeProbability(d1)
        val nd2 = normalDistrib.cumulativeProbability(d2)
        return discountFactor * (forward * nd1 - strike * nd2)
    }

    fun putPrice(
            forward: Double,
            strike: Double,
            discountFactor: Double,
            volatility: Double,
            timeToMaturity: Double
    ): Double {
        val vt = volatility * sqrt(timeToMaturity)
        val d1 = ln(forward / strike) / vt + vt / 2
        val d2 = d1 - vt
        val nd1 = normalDistrib.cumulativeProbability(-d1)
        val nd2 = normalDistrib.cumulativeProbability(-d2)
        return discountFactor * (strike * nd2 - forward * nd1)
    }

    fun callDelta(
            forward: Double,
            strike: Double,
            volatility: Double,
            timeToMaturity: Double
    ): Double {
        val vt = volatility * sqrt(timeToMaturity)
        val d1 = ln(forward / strike) / vt + vt / 2
        val nd1 = normalDistrib.cumulativeProbability(d1)
        return nd1
    }


}