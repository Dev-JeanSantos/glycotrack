plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.2.6"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("plugin.noarg") version "1.9.25"

}

noArg {
    annotation("jakarta.persistence.Entity")
}
group = "com.glycotrack"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17
repositories { mavenCentral() }
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
