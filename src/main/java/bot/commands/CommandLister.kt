package bot.commands

import bot.Bot
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

class CommandLister(bot: Bot) : Command(bot, false, "Commands", "Lists all available Commands!", "cmds", "help", "?") {
    override fun handle(event: MessageReceivedEvent) {
        val builder = EmbedBuilder()
        builder.setTitle("Commands", null)
        builder.setColor(Color.BLACK)
        builder.setDescription("All Commands")
        for (cmd in BotCommands.cmds()) {
            if (!cmd.isHidden) {
                builder.addField(Bot.PREFIX + cmd.name, cmd.description, true)
                builder.addField("Aliases", cmd.aliases.joinToString(", "), true)
                builder.addBlankField(true)
            }
        }
        event.channel.sendMessage(builder.build()).queue { msg: Message -> msg.addReaction("U+2139").queue() }
    }
}