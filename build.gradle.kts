import org.jetbrains.kotlin.konan.properties.loadProperties
import java.util.*

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.google.devtools.ksp")
    id("sts-dependencies.dependencies")
}

group = "com.github.kobting"
version = "0.0.1"

val keys = try {
    loadProperties("${rootProject.projectDir.path}/keys/keys.properties")
} catch(ex: Exception) {
    ex.printStackTrace()
    logger.warn("Missing keys to get dependencies from GitHub")
    Properties()
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/Kobting/STS-Annotations")
        credentials {
            username = keys.getOrDefault("username", "").toString()
            password = keys.getOrDefault("password", "").toString()
        }
    }
    mavenLocal()
}

kotlin {
    sourceSets {
        getByName("main") {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
    }
}

ksp {
    arg("resourceBasePath", "kobting/thepain")
}

dependencies {
    implementation(SlayTheSpire)
    implementation(ModTheSpire)
    implementation(BaseMod)
    implementation("com.github.kobting.sts-annotations:annotations:0.8.0")

    ksp("com.github.kobting.sts-annotations:processors:0.8.0")
    ksp(SlayTheSpire)
    ksp(ModTheSpire)
    ksp(BaseMod)
}

kotlin {
    jvmToolchain(8)
}