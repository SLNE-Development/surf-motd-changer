package dev.slne.surf.motdchanger

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun motdReloadCommand() = commandAPICommand("motdreload") {
    withPermission("surf.motdchanger.reload")

    anyExecutor { sender, args ->
        MotdConfig.reloadFromFile()
        sender.sendText {
            appendPrefix()
            success("MOTD configuration reloaded successfully.")
        }
    }
}