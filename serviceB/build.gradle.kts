plugins {
    id(Plugs.gradleApplication)
}

application {
    mainClassName = "quoter.services.serviceB.Starter"
}

dependencies{
    implementation(project(Modules.helper))
    implementation(project(Modules.model))
    implementation(Libs.khttp)
}