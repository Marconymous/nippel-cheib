package bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    private final boolean isHidden;
    private final String name;
    private final String description;
    private final String[] aliases;

    public Command(boolean isHidden, String name, String description, String ... aliases) {
        this.isHidden = isHidden;
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    public abstract void handle(MessageReceivedEvent event);

    public boolean isHidden() {
        return isHidden;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }
}
