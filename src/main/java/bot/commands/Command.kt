package bot.commands

import bot.Bot
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class Command(
    val bot: Bot,
    val isHidden: Boolean,
    val name: String,
    val description: String,
    vararg aliases: String
) {
    val aliases: Array<String>
    abstract fun handle(event: MessageReceivedEvent)

    init {
        this.aliases = Array(aliases.size){""}

        for ((i, a) in aliases.withIndex()) {
            this.aliases[i] = a
        }
    }
}