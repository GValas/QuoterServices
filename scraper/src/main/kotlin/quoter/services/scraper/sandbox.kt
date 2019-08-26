package quoter.services.scraper

fun main() {

    val imgUrl = """img={"url":"//v.seloger.com/s/width/800/visuels/0/x/v/m/0xvmumwg0bdz4npsnafqj39cn9bynug987xcj50cg.jpg","type":"bg"}"""
    val regex = """v\.seloger.*\.jpg""".toRegex()
    val x = regex.find(imgUrl)?.value
    println(x)

}