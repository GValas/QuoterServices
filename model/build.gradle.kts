plugins {
    id(Plugs.kotlinJpa) version Plugs.kotlinJpaVersion      // used for noarg consructor
}

dependencies {
    implementation(Libs.springBootStarterDataJpa)
    implementation(Libs.jacksonKotlin)
}