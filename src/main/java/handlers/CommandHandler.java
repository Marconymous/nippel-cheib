package handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandHandler {
    public void handle(MessageReceivedEvent event);
}
