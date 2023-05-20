package command

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder

abstract class Command(val name: String,val Description:String) {

    abstract fun create(guild : GlobalChatInputCreateBuilder)

    abstract suspend fun whenCalled(res : DeferredPublicMessageInteractionResponseBehavior, kord : Kord, guild: GuildChatInputCommandInteractionCreateEvent)

}