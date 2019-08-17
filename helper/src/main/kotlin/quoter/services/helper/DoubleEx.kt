package quoter.services.helper

fun Double.roundValueEx(numberOfDecimals: Int = 2): Double {
    return "%.${numberOfDecimals}f".format(this).toDouble()
}