package dev.slne.surf.motdchanger

import dev.slne.surf.surfapi.core.api.config.createSpongeYmlConfig
import dev.slne.surf.surfapi.core.api.config.manager.SpongeConfigManager
import dev.slne.surf.surfapi.core.api.config.surfConfigApi
import net.kyori.adventure.text.Component
import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class MotdConfig(
    val servers: Map<String, MotdServerConfig> = emptyMap()
) {

    @ConfigSerializable
    data class MotdServerConfig(
        val maxPlayerCount: Int? = null,
        val playerCount: Set<String> = mutableSetOf(),
        val motd: Component = Component.empty(),
        val favicon: String? = null
    )

    fun getServerConfig(address: String) = servers[address]
        ?: servers[address.split(":").first()] // Fallback to the server without port if not found

    companion object {
        private val manager: SpongeConfigManager<MotdConfig>

        init {
            surfConfigApi.createSpongeYmlConfig<MotdConfig>(plugIn.dataPath, "config.yml")
            manager = surfConfigApi.getSpongeConfigManagerForConfig(MotdConfig::class.java)
        }

        fun get(): MotdConfig = manager.config

        fun reloadFromFile() {
            manager.reloadFromFile()
        }
    }
}

val config get() = MotdConfig.get()