package me.cliff.bot.command.commads

import dev.kord.common.Color
import dev.kord.common.entity.Permission
import dev.kord.core.Kord
import dev.kord.core.behavior.ban
import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.Image
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import dev.kord.rest.builder.interaction.string
import dev.kord.rest.builder.interaction.user
import dev.kord.rest.builder.message.EmbedBuilder
import dev.kord.rest.request.RestRequestException
import me.cliff.bot.command.Command

object BanCommand: Command("ban","Ban's a User") {
    override fun create(guild: GlobalChatInputCreateBuilder) {
        guild.user("user","the user you want to ban"){
            required = true
        }
        guild.string("reason","the reason of the ban")
    }

    override suspend fun whenCalled(
        res: DeferredPublicMessageInteractionResponseBehavior,
        kord: Kord,
        guild: GuildChatInputCommandInteractionCreateEvent
    ) {

        val userid = guild.interaction.command.users["user"]?.id
        val reason = guild.interaction.command.strings["reason"]
        val user = kord.getUser(userid!!)?.username
        val avatar = kord.getUser(userid)?.avatar?.cdnUrl?.toUrl {
            this.size = Image.Size.Size256
        }

        try {
            if (guild.interaction.user.getPermissions().contains(Permission.BanMembers)) {
                guild.interaction.getGuild().ban(userid) {
                    this.reason = reason ?: "No reason given"
                }

                val embed = EmbedBuilder().apply {
                    title = "User Banned"
                    color = Color(0, 80, 255)
                    field {
                        name = "User:"
                        value = user!!
                    }
                    thumbnail {
                        this.url = avatar!!
                    }

                }
                res.respond { embeds = mutableListOf(embed) }
            }else{
                res.respond { content = "You Dont have ban Perms" }
            }
        }catch (e: RestRequestException){
            println(e.toString())
            if (e.toString().contains(" Missing bot Permissions",ignoreCase = true)){
                res.respond { content = "The bot doesn't have perms to ban users" }
            }
        }

        try {
            kord.getUser(userid)?.getDmChannel()?.createMessage(reason ?: "No reason given")
        }catch (e: RestRequestException){
            println("user doesn't allow dm chat")
        }

    }

}