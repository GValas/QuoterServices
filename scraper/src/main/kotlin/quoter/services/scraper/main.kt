@file:JvmName("Starter")

package quoter.services.scraper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import quoter.services.scraper.Const.urlRoot
import java.io.File


object Const {
    const val urlRoot: String = "https://www.seloger.com/list.htm" +
            "?qsversion=1.0" +      // query string version
            "&projects=2" +         // vente(1), achat(2), vente
            "&natures=1,2" +        // neuf(1), ancien(2), viager(3), renovation(4)
            "&enterprise=0"         // pro
}

typealias Buckets = Pair<Int?, Int?>


enum class ProjectType(val type: Int) {
    Flat(1)
}

fun Buckets.toBucketString(): String {
    val a = this.first?.toString() ?: "NaN"
    val b = this.second?.toString() ?: "NaN"
    return "$a/$b"
}

data class SeLogerRequest(
        val types: List<ProjectType>,
        val price: Buckets,
        val places: List<Int>,
        val rooms: Int,
        val surface: Buckets
) {
    fun urlRequest(page: Int = 1) = urlRoot +
            "&types=${types.map { it.type }.joinToString(",")}" +
            "&price=${price.toBucketString()}" +
            "&places=[{ci:750111}|{ci:750103}]" +
            "&rooms=$rooms" +
            "&surface=${surface.toBucketString()}" +
            "&LISTING-LISTpg==$page "
}


private val logger = KotlinLogging.logger {}

fun main() = runBlocking {

    val req = SeLogerRequest(
            types = listOf(ProjectType.Flat),
            price = Pair(null, 4000000),
            places = listOf(75011),
            rooms = 2,
            surface = Pair(10, 100))


    logger.info { "request: ${req.urlRequest()}" }

    //logger.info { "doc: $doc" }

    // get page number
    val doc = Jsoup.connect(req.urlRequest()).get()

    File("src/resources/doc.txt").writeText(doc.toString())


    val nbPages = doc.select("div[class=pagination-number] > a:last-of-type > span").last().text().toInt()
    repeat(nbPages) {
        parseOffersList(it, req)
    }

}

private fun CoroutineScope.parseOffersList(it: Int, req: SeLogerRequest) {
    launch(Dispatchers.IO) {
        val page = it + 1
        Jsoup.connect(req.urlRequest(page)).get().run {
            select("a[class=c-pa-link link_AB]").forEach {
                parseOfferContent(it, page)
            }
        }
    }
}

private fun CoroutineScope.parseOfferContent(it: Element, page: Int) {
    launch(Dispatchers.IO) {
        val offerUrl = it.attr("href")
        logger.info { "page[$page], offerUrl=$offerUrl" }
        Jsoup.connect(offerUrl).get().run {
            select("div[class=carrousel_slide][data-lazy]").forEach() {
                val imgUrl = it.attr("data-lazy")
                logger.info { "img=$imgUrl" }


                // extracting picture url
                val regex = """v\.seloger.*\.jpg""".toRegex()
                val x = regex.find(imgUrl)
                println(x)

            }
        }
    }
}