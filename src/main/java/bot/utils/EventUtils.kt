package bot.utils

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object EventUtils {
    fun rawContent(event: MessageReceivedEvent): String {
        return event.message.contentRaw
    }

    fun spitRawContent(event: MessageReceivedEvent): List<String> {
        return rawContent(event).split("\\s".toRegex())
    }

    fun sendMessageToChannel(event: MessageReceivedEvent, msg: String?) {
        event.channel.sendMessage(msg!!).queue()
    }
}