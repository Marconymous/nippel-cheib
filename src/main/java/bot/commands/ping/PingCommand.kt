package bot.commands.ping

import bot.Bot
import bot.commands.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class PingCommand(bot: Bot) : Command(bot, false, "ping", "Pings to Bot to check the Connection!", "") {
    override fun handle(event: MessageReceivedEvent) {
        event.channel.sendMessage("Pong -- Response in *0ms*!").queue()
    }
}