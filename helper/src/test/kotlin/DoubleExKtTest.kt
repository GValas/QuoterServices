import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import quoter.services.helper.roundValueEx

internal class DoubleExKtTest {
    @Test
    fun roundValueEx() {
        assertEquals(3.14159265359.roundValueEx(4), 3.1416)
        assertEquals(1.0.roundValueEx(4), 1.00)
    }
}