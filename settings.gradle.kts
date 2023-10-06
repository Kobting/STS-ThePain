pluginManagement {

    plugins {
        id("com.google.devtools.ksp") version "1.7.22-1.0.8"
    }

    val keys = try {
        val properties = java.util.Properties()
        with(File("${rootProject.projectDir.path}/keys/keys.properties")) {
            bufferedReader().use(properties::load)
        }
        properties
    } catch(ex: Exception) {
        ex.printStackTrace()
        logger.warn("Missing keys to get dependencies from GitHub")
        java.util.Properties()
    }

    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
        maven {
            url = uri("https://maven.pkg.github.com/Kobting/STS-Annotations")
            credentials {
                username = keys.getOrDefault("username", "").toString()
                password = keys.getOrDefault("password", "").toString()
            }
        }
    }


}



buildscript {
    dependencies {
        classpath("com.github.kobting:sts-dependencies:1.0.0")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "the-pain"