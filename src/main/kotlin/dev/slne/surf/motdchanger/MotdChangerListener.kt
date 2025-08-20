package dev.slne.surf.motdchanger

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyPingEvent
import com.velocitypowered.api.util.Favicon
import kotlin.jvm.optionals.getOrNull

object MotdChangerListener {

    @Subscribe
    fun onProxyPing(event: ProxyPingEvent) {
        val address = event.connection.virtualHost.getOrNull() ?: return
        val config = config.getServerConfig(address.hostName) ?: return

        val maxPlayerCount = config.maxPlayerCount
        val playerCount = getPlayerCount(config.playerCount)
        val motd = config.motd
        val favicon = config.favicon

        event.ping = event.ping.asBuilder()
            .apply {
                maxPlayerCount?.let { maximumPlayers(it) }
                onlinePlayers(playerCount)
                description(motd)
                favicon?.let { favicon(Favicon(favicon)) }
            }
            .build()
    }

    private fun getPlayerCount(servers: Set<String>): Int =
        plugIn.proxy.allServers.filter { it.serverInfo.name in servers }
            .sumOf { it.playersConnected.size }
}