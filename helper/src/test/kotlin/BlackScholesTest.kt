import quoter.services.helper.BlackScholes.callPrice
import quoter.services.helper.BlackScholes.putPrice
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BlackScholesTest {

    private val spot = 100.0
    private val rate = 0.08
    private val volatility = 0.30

    private val timeToMaturity = 1.0
    private val strike = spot

    private val discountFactor = Math.exp(-rate * timeToMaturity)
    private val forward = spot / discountFactor

    @Test
    fun priceTests() {

        val call = callPrice(forward, strike, discountFactor, volatility, timeToMaturity)
        assertEquals(call, 15.7113, 0.0001)

        val put = putPrice(forward, strike, discountFactor, volatility, timeToMaturity)
        assertEquals(put, 8.0229, 0.0001)
        assertEquals(call - put, discountFactor * (forward - strike), 0.0001)

        val call0 = callPrice(forward, 0.0, 1.0, volatility, timeToMaturity)
        assertEquals(call0, forward, 0.0001)
    }


}