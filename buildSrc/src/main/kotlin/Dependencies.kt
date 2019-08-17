object Project {
    const val name = "quoter-services"
    const val group = "quoter"
    const val version = "1.0-SNAPSHOT"
}

object Modules {
    const val model = ":model"
    const val helper = ":helper"
    const val serviceA = ":serviceA"
    const val serviceB = ":serviceB"
    const val app = ":app"
}

object Versions {
    const val kotlin = "1.3.40"
    const val jvm = "1.8"
    const val spring = "2.1.6.RELEASE"
}

object Plugs {

    const val gradleBase = "org.gradle.base"
    const val gradleApplication = "org.gradle.application"

    const val kotlinJvm = "org.jetbrains.kotlin.jvm"
    const val kotlinJvmVersion = Versions.kotlin

    const val kotlinJpa = "org.jetbrains.kotlin.plugin.jpa"
    const val kotlinJpaVersion = Versions.kotlin

    const val kotlinNoarg = "org.jetbrains.kotlin.plugin.noarg"
    const val kotlinNoargVersion = Versions.kotlin

    const val kotlinSpring = "org.jetbrains.kotlin.plugin.spring"
    const val kotlinSpringVersion = Versions.kotlin

    const val springDependencyManagement = "io.spring.dependency-management"
    const val springDependencyManagementVersion = "1.0.7.RELEASE"

    const val springBoot = "org.springframework.boot"
    const val springBootVersion = "2.1.6.RELEASE"

}

object Libs {

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0-M2"

    const val springBootStarterDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa:${Versions.spring}"
    const val springBootStarterDataRest = "org.springframework.boot:spring-boot-starter-data-rest:${Versions.spring}"
    const val springBootStarterWeb = "org.springframework.boot:spring-boot-starter-web:${Versions.spring}"
    const val springBootStarterSecurity = "org.springframework.boot:spring-boot-starter-security:${Versions.spring}"

    const val auth0JavaJwt = "com.auth0:java-jwt:3.4.0"

    const val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:2.9.9"

    const val h2 = "com.h2database:h2"

    const val khttp = "khttp:khttp:1.0.0"

    const val apacheCommonsMath3 = "org.apache.commons:commons-math3:3.6.1"

    const val sqliteJdbc = "org.xerial:sqlite-jdbc:3.28.0"
    const val sqliteDialect = "com.zsoltfabok:sqlite-dialect:1.0"

}

object TestLibs {
    const val springBootStarterTest = "org.springframework.boot:spring-boot-starter-test:${Versions.spring}"
    const val springSecurityTest = "org.springframework.security:spring-security-test:5.1.5.RELEASE"
    const val assertjCore = "org.assertj:assertj-core:3.12.2"
    const val junitApi = "org.junit.jupiter:junit-jupiter-api:5.4.2"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:5.4.2"
}
