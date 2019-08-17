@file:JvmName("Starter")
package quoter.services.serviceB

import khttp.get

fun main() {
    val r = get("https://api.github.com/events")
    println(r.statusCode)
}