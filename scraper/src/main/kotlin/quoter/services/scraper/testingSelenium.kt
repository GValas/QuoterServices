package quoter.services.scraper

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import org.openqa.selenium.chrome.ChromeOptions
import javax.annotation.RegEx


fun main(args: Array<String>) {


    System.setProperty("webdriver.chrome.driver",
            """C:\Users\Gege\Software\chromedriver_win32\chromedriver.exe""")

    val driver = ChromeDriver()


    driver.get("https://www.seloger.com/annonces/achat/appartement/paris-11eme-75/149372225.htm?enterprise=0&natures=1,2&places=%5b%7bci%3a750111%7d%7c%7bci%3a750103%7d%5d&price=NaN%2f4000000&projects=2&qsversion=1.0&rooms=2&surface=10%2f100&types=1&bd=ListToDetail")


    //val doc = driver.findElementById("js-descriptifBien").text


//    val x = driver
//            .findElements(By.xpath("//img[@class='carrousel_image_visu carrousel_image_small']"))
//            .map {it.getAttribute("src")};
//
//    val html = driver.findElementByTagName("html")

    val x = driver
            .findElementsByXPath("//div")
            .map {it.getAttribute("data-lazy")}
            .filterNotNull()
            .filter{it.contains(Regex(".*visuels.*jpg"))  }

    File("doc.txt").writeText(x.joinToString { "\\n" })

//    val titles = driver.findElementsByClassName("post-right")
//            .map { it.findElement(By.tagName("H5")) }
//            .map { it.findElement(By.tagName("A")) }
//            .map { it.text }
//
//    val new = titles - history
//    if (new.isEmpty()) {
//        println("Nothing new on But Wait Why?")
//    } else {
//        println("New articles:")
//        new.forEach {
//            println(it)
//            file.appendText("$it\n")
//        }
//    }

    driver.close()
}