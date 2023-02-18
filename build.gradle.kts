import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
    id("com.apollographql.apollo3").version("3.7.3")
    id("org.openjfx.javafxplugin").version("0.0.9")
}

group = "me.jorgetargz"
version = "1.0-SNAPSHOT"

application {
    mainModule.set("graphqlKotlin")
    mainClass.set("me.jorgetargz.main.MainFX")
}

repositories {
    mavenCentral()
    maven { url = uri("https://repository.apache.org/snapshots") }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.6.4")

    implementation("com.apollographql.apollo3:apollo-api:3.7.3")
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")

    // optional: if you want to use the normalized cache
    //implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.3")

    implementation("org.apache.logging.log4j:log4j-api:2.17.1")
    implementation("org.apache.logging.log4j:log4j-core:2.17.1")

    implementation("org.openjfx:javafx-base:17")
    implementation("org.openjfx:javafx-fxml:17")

    implementation("io.github.palexdev:materialfx:11.13.3") {
        exclude("org.openjfx","javafx-base")
        exclude("org.openjfx","javafx-fxml")
        exclude("org.openjfx","javafx-controls")
        exclude("org.openjfx","javafx-graphics")
    }

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


javafx {
    version = "17.0.1"
    modules = listOf("javafx.controls","javafx.fxml")
}

apollo {
    service("localhost") {
        sourceFolder.set("me/jorgetargz/localhost")
        packageName.set("me.jorgetargz.localhost")
    }
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
}

