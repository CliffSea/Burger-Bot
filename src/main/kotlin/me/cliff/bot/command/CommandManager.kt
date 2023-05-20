package me.cliff.bot.command

import me.cliff.bot.command.commads.AvatarCommand
import me.cliff.bot.command.commads.PingCommand
import me.cliff.bot.command.commads.ServerCommand
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent


class CommandManager {
    private val commands = arrayOf(
        PingCommand,
        ServerCommand,
        AvatarCommand,
    )

  suspend fun register(k: Kord){
        commands.forEach {
            k.createGlobalChatInputCommand(it.name,it.Description) {
                it.create(this)
            }
        }
    }

    suspend fun run(cC: String, guild: GuildChatInputCommandInteractionCreateEvent, kord: Kord) {
        val command = commands.find { it.name.equals(cC, ignoreCase = true) }

        if (command != null) {
            val response = guild.interaction.deferPublicResponse()
            command.whenCalled(response, kord, guild)
        } else {
            val response = guild.interaction.deferPublicResponse()
            response.respond { content = "Command Not Found" }
        }
    }

}