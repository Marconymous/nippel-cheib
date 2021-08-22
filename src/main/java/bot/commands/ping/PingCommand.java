package bot.commands.ping;

import bot.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public final class PingCommand extends Command {
    public PingCommand() {
        super(false, "ping", "Pings to Bot to check the Connection!", "");
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        event.getChannel().sendMessage("--pong").queue();
    }
}
