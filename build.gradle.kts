import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.2.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "2.2.10"
    id("com.google.devtools.ksp") version "2.2.10-2.0.2"
    id("io.micronaut.application") version "4.6.1"
    id("com.gradleup.shadow") version "8.3.9"
    id("io.micronaut.aot") version "4.6.1"
}

allOpen {
    // Micronaut needs controllers (and AOP targets) to be open for proxies/validation
    annotation("io.micronaut.http.annotation.Controller")
    annotation("io.micronaut.aop.Around")
    annotation("jakarta.inject.Singleton")
}

version = "0.1"
group = "com.example"

val kotlinVersion=project.properties.get("kotlinVersion")
val micronautVersion=project.properties.get("micronautVersion")
repositories {
    mavenCentral()
}


dependencies {
    // Align all Micronaut module versions to the same release
    implementation(platform("io.micronaut.platform:micronaut-platform:$micronautVersion"))

    // Micronaut Data MongoDB
    implementation("io.micronaut.data:micronaut-data-mongodb")
    ksp("io.micronaut.data:micronaut-data-document-processor:$micronautVersion")

    // Micronaut MongoDB configuration
    implementation("io.micronaut.mongodb:micronaut-mongo-sync")
    implementation("org.mongodb:mongodb-driver-sync:4.11.0")

    // KSP processors
    ksp("io.micronaut.validation:micronaut-validation-processor")
    ksp("io.micronaut.serde:micronaut-serde-processor")

    // Core runtime
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut:micronaut-http-server-netty")

    // Validation
    implementation("io.micronaut.validation:micronaut-validation")

    // Kotlin libs
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")

    // HTTP client
    testImplementation("io.micronaut:micronaut-http-client")

    //Swagger
    runtimeOnly("org.yaml:snakeyaml:2.2")
    ksp("io.micronaut.openapi:micronaut-openapi:6.19.3")
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations:6.19.3")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.21")

    // Logging / Jackson
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}



application {
    mainClass = "com.example.ApplicationKt"
}
java {
    // compilation target
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    // force Gradle to use JDK 21
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    // keep using Java 21 for toolchains
    jvmToolchain(21)

    // NEW: unified compiler options (replaces kotlinOptions)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)          // instead of jvmTarget = "21"
        freeCompilerArgs.add("-Xjsr305=strict")  // instead of += listOf(...)
    }
}


graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}


