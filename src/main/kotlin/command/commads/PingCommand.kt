package command.commads

import command.Command
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder

object PingCommand: Command("ping","Display the latency") {
    override fun create(guild: GlobalChatInputCreateBuilder) {
    }

    override suspend fun whenCalled(res: DeferredPublicMessageInteractionResponseBehavior, kord: Kord, guild: GuildChatInputCommandInteractionCreateEvent) {
        val startTime = System.currentTimeMillis()
        val latency = System.currentTimeMillis() - startTime
        val gatewayLatency = kord.gateway.averagePing
        res.respond {content =  "Pong! Gateway latency: ${gatewayLatency}ms, Message round-trip time: ${latency}ms" }
    }


}