pluginManagement {
    repositories {
        maven("https://repo.slne.dev/repository/maven-public/") { name = "maven-public" }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}


rootProject.name = "surf-motd-changer"