package me.cliff.bot.command.commads

import me.cliff.bot.command.Command
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import dev.kord.common.Color
import dev.kord.rest.Image
import dev.kord.rest.builder.message.EmbedBuilder
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object ServerCommand: Command("server","Says Info about the server") {
    override fun create(guild: GlobalChatInputCreateBuilder) {
    }

    override suspend fun whenCalled(res: DeferredPublicMessageInteractionResponseBehavior, kord: Kord, guild: GuildChatInputCommandInteractionCreateEvent) {
       val n  = guild.interaction.getGuild().name
        val id = guild.interaction.getGuild().id
        val members = guild.interaction.getGuild().memberCount
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")





        val embed = EmbedBuilder().apply {
            title = "$n Server Info"
            color = Color(0,80,255)
            field {
                name = "Member Count"
                value = "$members"
                inline = true
            }
            field{
                name = "Server id"
                value = "$id"
                inline = true
            }
            thumbnail{
                this.url = guild.interaction.getGuild().icon?.cdnUrl?.toUrl{
                    this.size = Image.Size.Size512
                }.toString()
            }
            footer {
                text = "Burger | ${currentTime.format(formatter)}"
            }
        }
        res.respond { embeds = mutableListOf(embed) }
    }
}