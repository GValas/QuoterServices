package quoter.services.model

data class Quote(
    val premium: Double,
    val delta: Double,
    val forward: Double,
    val implicitVolatility: Double,
    val discountFactor: Double
)