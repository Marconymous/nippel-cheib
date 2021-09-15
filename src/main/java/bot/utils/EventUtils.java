package bot.utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public final class EventUtils {
    public static String rawContent(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    public static String[] spitRawContent(MessageReceivedEvent event) {
        return rawContent(event).split("\\s");
    }

    public static void sendMessageToChannel(MessageReceivedEvent event, String msg) {
        event.getChannel().sendMessage(msg).queue();
    }
}
