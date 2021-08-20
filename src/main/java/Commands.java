import handlers.CommandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public enum Commands {
    PING(
            event -> {
                event.getChannel().sendMessage("--pong").queue();
            },
            "ping"
    ),

    TOEBEL(
            event -> {
                event.getChannel().sendMessage("<@692770670605500437> isch immer am schisse!").queue();
            },
            "toebel", "töbel", "globi", "globias", "tobias"
    );

    private final String[] aliases;
    private final CommandHandler handler;

    Commands(CommandHandler handler, String ... aliases) {
        this.aliases = aliases;
        this.handler = handler;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void handle(MessageReceivedEvent event) {
        handler.handle(event);
    }

    private static Commands[] commands;

    public static Commands[] list() {
        if (commands == null) {
            commands = Commands.values();
        }
        return commands;
    }
}
