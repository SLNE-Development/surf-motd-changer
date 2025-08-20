package dev.slne.surf.motdchanger

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyPingEvent
import kotlin.jvm.optionals.getOrNull

object MotdChangerListener {

    @Subscribe
    fun onProxyPing(event: ProxyPingEvent) {
        val address = event.connection.virtualHost.getOrNull() ?: return
        val config = config.getServerConfig(address.hostName) ?: return

        val maxPlayerCount = config.maxPlayerCount
        val playerCount = getPlayerCount(config.playerCount)
        val motd = config.motd

        event.ping = event.ping.asBuilder()
            .apply {
                maxPlayerCount?.let { maximumPlayers(it) }
                onlinePlayers(playerCount)
                description(motd)
            }
            .build()
    }

    private fun getPlayerCount(servers: Set<String>): Int =
        plugIn.proxy.allServers.filter { it.serverInfo.name in servers }
            .sumOf { it.playersConnected.size }
}