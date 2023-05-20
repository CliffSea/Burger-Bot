import command.CommandManager
import dev.kord.common.annotation.KordPreview
import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import io.github.cdimascio.dotenv.dotenv

val dotenv = dotenv()
val token: String = dotenv["TOKEN"]

@KordPreview
suspend fun main() {


    val client = Kord(token)

    CommandManager().register(client)

    client.on<ReadyEvent> {
        println("Bot online :>")
    }

    client.on<GuildChatInputCommandInteractionCreateEvent> {

        println(this.interaction.command.rootName)
        CommandManager().run(this.interaction.command.rootName,this,client)
    }

    client.login()

}