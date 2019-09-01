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
import khttp.get
import kotlin.math.max


object Const {
    const val urlRoot: String = "https://www.seloger.com/list.htm" +
            "?qsversion=1.0" +      // query string version
            "&projects=2" +         // vente(1), achat(2), vente
            "&natures=1,2" +        // neuf(1), ancien(2), viager(3), renovation(4)
            "&enterprise=0"         // pro

    object RegEx {
        const val properties = "Object\\.defineProperty\\( ConfigDetail, '([^']*)', {\\n\\s+value: \"([^\"]*)\","
        const val pictures = "(v\\.seloger\\.com/s/(width|height)/(800|799)/visuels/.*\\.jpg)\""
        const val annonces = "\"(https://www.seloger.com/annonces/achat/.*=ListToDetail)\""
    }

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

    val url = req.urlRequest()
    //val headers = mapOf("user-agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")

    // get page number
    val res = get(url)
    val doc = res.text
    File("doc.txt").writeText(doc.toString())

    val regex = "LISTpg=(\\d*)".toRegex()
    val maxPg = regex.findAll(doc)
            .map { it -> it.groups[1] }
            .filterNotNull()
            .map { it.value.toInt() }
            .max()

    if (maxPg != null) {
        repeat(maxPg) {
            parsePageList(it + 1, req)
        }
    }


}

private fun CoroutineScope.parsePageList(pageId: Int, req: SeLogerRequest) {
    launch(Dispatchers.IO) {

        // getting page
        val pageUrl = req.urlRequest(pageId)
        val pageContent = get(pageUrl).text
        logger.info { "page[$pageId], offerUrl=$pageUrl" }

        // parse annonces
        Const.RegEx.annonces.toRegex()
                .findAll(pageContent)
                .map { it -> it.groups[1]?.value }
                .distinct().toList().distinct()     // ???
                .filterNotNull()
                .forEach {

                    parseAnnonce(it)
                }

    }
}


private fun CoroutineScope.parseAnnonce(pageUrl: String) {
    launch(Dispatchers.IO) {

        // getting page
        val pageContent = get(pageUrl).text
        logger.info { "url=$pageUrl" }

        // parsing page
        val regx = Const.RegEx.pictures.toRegex()
        val pics = regx.findAll(pageContent)
        pics.forEach {
            println(it.value)
        }


    }
}