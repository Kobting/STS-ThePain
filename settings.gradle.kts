pluginManagement {

    plugins {
        id("com.google.devtools.ksp") version "1.7.22-1.0.8"
    }

    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }


}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "the-pain"