plugins {
    kotlin("jvm") version "1.8.20"
    java
    application
}

group = "me.cliff.bot"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo1.maven.org/maven2/")
}
dependencies {
    testImplementation(kotlin("test"))
    implementation("dev.kord:kord-core:0.9.x-SNAPSHOT")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.5.0")
    // https://mvnrepository.com/artifact/io.github.humbleui/skija-windows-x64
    implementation("io.github.humbleui:skija-windows-x64:0.109.2")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "me.cliff.bot.MainKt"
    }
    //duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    //from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

application {
    mainClass.set("me.cliff.bot.MainKt")
}
