package dev.slne.surf.motdchanger

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import java.nio.file.Path

lateinit var plugIn: MotdChanger

class MotdChanger @Inject constructor(
    val proxy: ProxyServer,
    @param:DataDirectory val dataPath: Path
) {
    init {
        plugIn = this
    }

    @Subscribe
    fun onProxyInitialize(event: ProxyInitializeEvent) {
        proxy.eventManager.register(this, MotdChangerListener)
        motdReloadCommand()
    }
}