plugins {
    id(Plugs.gradleApplication)
}

application {
    mainClassName = "quoter.services.scraper.Starter"
}

dependencies{
    implementation(Libs.jsoup)
    implementation(Libs.springBootStarterWeb)
}