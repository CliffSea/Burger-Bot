package command.commads

import command.Command
import dev.kord.common.Color
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.Image
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import dev.kord.rest.builder.interaction.user
import dev.kord.rest.builder.message.EmbedBuilder

object AvatarCommand: Command("avatar","Get the user profile picture") {

    override fun create(guild: GlobalChatInputCreateBuilder) {
        guild.user("user","the user you want to see his pfp")
    }

    override suspend fun whenCalled(
        res: DeferredPublicMessageInteractionResponseBehavior,
        kord: Kord,
        guild: GuildChatInputCommandInteractionCreateEvent
    ) {

        val us = guild.interaction.command.users["user"]?.id
        val id = us ?: guild.interaction.user.id
        val name = kord.getUser(id)?.username
        val av = kord.getUser(id)?.avatar?.cdnUrl?.toUrl {
            this.size = Image.Size.Size1024
        }

        val embed = EmbedBuilder().apply {
            title = "$name's profile picture"
            color = Color(255, 175, 175)
            image = "$av"
        }

        res.respond { embeds = mutableListOf(embed) }
    }
}