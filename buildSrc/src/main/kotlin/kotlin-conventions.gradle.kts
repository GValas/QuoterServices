import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    jcenter()
}

plugins {
    id("org.jetbrains.kotlin.jvm")  // can't use Plugs.kotlinJvm ???!!??
}

dependencies {
    implementation(Libs.kotlinStdLib)
    implementation(Libs.kotlinReflect)

    testImplementation(TestLibs.assertjCore)
    testImplementation(TestLibs.junitEngine)

    testRuntime(TestLibs.junitEngine)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = Versions.jvm
        allWarningsAsErrors = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// tasks.withType<Delete>{
//    delete(buildDir)
// }