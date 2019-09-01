plugins {
    id(Plugs.gradleApplication)
}

application {
    mainClassName = "quoter.services.scraper.Starter"
}

dependencies{
    implementation(Libs.jsoup)
    implementation(Libs.springBootStarterWeb)
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation(Libs.khttp)
}