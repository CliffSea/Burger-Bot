package me.cliff.bot.command.commads

import dev.kord.common.Color
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.Image
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import dev.kord.rest.builder.interaction.string
import dev.kord.rest.builder.message.EmbedBuilder
import me.cliff.bot.command.Command

object UnBanCommand: Command("unban","unban a user") {
    override fun create(guild: GlobalChatInputCreateBuilder) {
        guild.string("id","id of the user"){
            required = true
        }
    }

    override suspend fun whenCalled(
        res: DeferredPublicMessageInteractionResponseBehavior,
        kord: Kord,
        guild: GuildChatInputCommandInteractionCreateEvent
    ) {

        val idString = guild.interaction.command.strings["id"]
        val id = idString!!.toLong()
        val avatar = kord.getUser(Snowflake(id))?.avatar?.cdnUrl?.toUrl {
            this.size = Image.Size.Size256
        }
        if (guild.interaction.user.getPermissions().contains(Permission.BanMembers)) {
                guild.interaction.getGuild().unban(Snowflake(id))

                val embed = EmbedBuilder().apply {
                    title = "User UnBanned"
                    color = Color(0, 80, 255)
                    field {
                        name = idString
                    }
                    thumbnail {
                        url = avatar!!
                    }
                }
                res.respond { embeds = mutableListOf(embed) }
            }else{
                res.respond { content = "You Dont have unban Perms" }
        }


    }
}