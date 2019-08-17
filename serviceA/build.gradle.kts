plugins {
    id(Plugs.gradleApplication)
    id(Plugs.springBoot) version Plugs.springBootVersion
    id(Plugs.springDependencyManagement) version Plugs.springDependencyManagementVersion
    id(Plugs.kotlinSpring) version Plugs.kotlinSpringVersion
    id(Plugs.kotlinJpa) version Plugs.kotlinJpaVersion      // used for noarg consructor
}

application {
    mainClassName = "quoter.services.serviceA.QuoterApplicationKt"
}

dependencies{

    // other project dependencies
    implementation(project(Modules.helper))
    implementation(project(Modules.model))

    // web server, client
    implementation(Libs.khttp)
    implementation(Libs.springBootStarterDataJpa)
    implementation(Libs.springBootStarterWeb)

    // security
    implementation(Libs.springBootStarterSecurity)
    implementation(Libs.auth0JavaJwt)

    // sqlite
    implementation(Libs.sqliteJdbc)
    implementation(Libs.sqliteDialect)

    // test
    testCompile(TestLibs.springSecurityTest)


}