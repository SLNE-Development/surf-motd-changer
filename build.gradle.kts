plugins {
    id("dev.slne.surf.surfapi.gradle.velocity") version "1.21.7+"
}

group = "dev.slne.surf"
version = "1.0.0-SNAPSHOT"

velocityPluginFile {
    main = "dev.slne.surf.motdchanger.MotdChanger"
    authors = listOf("twisti")
}